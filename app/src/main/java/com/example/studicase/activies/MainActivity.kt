package com.example.studicase.activies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studicase.R
import com.example.studicase.adapter.AdapterClass
import com.example.studicase.database.AppDatabase
import com.example.studicase.model.DataFromService
import com.example.studicase.model.DataServiceViewModel
import com.example.studicase.model.StudiCase
import com.example.studicase.model.StudiCaseViewModel
import com.example.studicase.service.Hasil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity() {

    lateinit var studyViewModel : DataServiceViewModel
    lateinit var studyCaseViewModel: StudiCaseViewModel
    var studyList = listOf<StudiCase>()
    var appDatabase: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getInstance(this)
        loadStudy()
//        fabAdd.setOnClickListener {
//            showAlertDialogAdd()
//        }

    }

    override fun onResume() {
        super.onResume()
        loadStudy()
    }

//    private fun showAlertDialogAdd() {
//        val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, null)
//        val mBuilder = AlertDialog.Builder(this)
//                .setView(mDialogView)
//        val  mAlertDialog = mBuilder.show()
//        mDialogView.btn_kirim.setOnClickListener {
//            mAlertDialog.dismiss()
//            if(mDialogView.edt_title.text.isEmpty()){
//                mDialogView.edt_title.setError("Judul tidak boleh kosong")
//            }
//            if(mDialogView.edt_deskripsi.text.isEmpty()){
//                mDialogView.edt_deskripsi.setError("Deskripsi tidak boleh kosong")
//            }
//            if(mDialogView.edt_userId.text.isEmpty()){
//                mDialogView.edt_userId.setError("UserId tidak boleh kosong")
//            }
//
//            studyCaseViewModel.insertStudy(
//
//                for( i)
//                StudiCase(
//                    userId = mDialogView.edt_userId.text.toString(),
//                    title = mDialogView.edt_title.text.toString(),
//                    body = mDialogView.edt_deskripsi.text.toString()
//                )
//            )
//        }
//        mDialogView.btn_cancel.setOnClickListener {
//            mAlertDialog.dismiss()
//        }
//
//    }


    fun loadStudy(){

        DataServiceViewModel.getData { result ->

            if(result!= null){

                when (result.status){

                    Hasil.Status.ERROR->{
                        Toast.makeText(this,"Error:"+ result.exception?.message, Toast.LENGTH_LONG).show()
                    }
                    Hasil.Status.SUCCESS ->{
                        val albums = result.data
                        albums?.let {
                            saveWith(it)
                        }
                    }
                }

            }
        }

    }

    private fun refreshUIWith(data: List<StudiCase>){

        val albumsList = rcView
        var layoutManager = LinearLayoutManager(this)
        albumsList.layoutManager = layoutManager
        albumsList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val adapter = AdapterClass(data)
        albumsList.adapter = adapter
    }

    private fun saveWith(it: Array<DataFromService>) {

        doAsync {
            val currentDBPath = getDatabasePath(AppDatabase.DB_NAME).absolutePath
            println("DBPath is " + currentDBPath)

            var items = ArrayList<StudiCase>()

            for (data in it){
                val item = StudiCase()
                item.userId = data.userId
                item.id = data.id
                item.title = data.title
                item.body  = data.body
                items.add(item)
            }
            appDatabase!!.kelasDao().insertStudy(items)
            println("$items")

            val musicAlbums = appDatabase?.kelasDao()?.getStudy()
            activityUiThread {
                longToast("Data berhasil disimpan di Room DB")
                refreshUIWith(musicAlbums!!)
            }
        }

    }

    private fun getNamesFromDb(searchText: String) {
        val searchTextQuery = "%$searchText%"
        appDatabase!!.kelasDao().getTitle(searchTextQuery)
            .observe(this, object : Observer<List<StudiCase>> {
                override fun onChanged(chapter: List<StudiCase>?) {
                    if (chapter == null) {
                        return
                    }
                    val adapter = AdapterClass(chapter)
                    rcView.adapter = adapter
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Memanggil/Memasang menu item pada toolbar dari layout menu_bar.xml
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val searchIem: MenuItem = menu.findItem(R.id.search_button)
        val searchView =
            searchIem.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Cari.."
        searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @SuppressLint("SetTextI18n")
            override fun onQueryTextSubmit(query: String): Boolean {
                //Untuk memfilter data dari ArrayAdapter
                getNamesFromDb(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(nextText: String): Boolean {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                getNamesFromDb(nextText)
                return false
            }
        })
        return true
    }
}