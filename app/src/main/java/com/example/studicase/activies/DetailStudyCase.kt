package com.example.studicase.activies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.studicase.R
import com.example.studicase.model.StudiCase
import com.example.studicase.model.StudiCaseViewModel
import kotlinx.android.synthetic.main.activity_detail_study_case.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class DetailStudyCase : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private var studyCase : StudiCase? = null
    lateinit var studyViewModel : StudiCaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_study_case)

        studyCase = intent.getParcelableExtra(EXTRA_DETAIL) as? StudiCase

//        studyViewModel = ViewModelProvider(this).get(
//            StudiCaseViewModel::class.java
//        )

        val namaDepan : String

        val nama = studyCase!!.title
        if(nama!!.isEmpty()){
            namaDepan = ""
        }else{
            namaDepan = nama.substring(0,1)
        }
        val colorGenerator = ColorGenerator.MATERIAL
        val color = colorGenerator.randomColor

        val imageView = TextDrawable.builder().buildRound(namaDepan,color)
        detail_imgColor.setImageDrawable(imageView)

        detail_userId.setText(studyCase!!.userId)
        detail_title.setText(studyCase!!.title)
        detail_deskripsi.setText(studyCase!!.body)

//        btn_update.setOnClickListener {
//
//            if(detail_title.text.isEmpty()){
//                detail_title.setError("Judul tidak boleh kosong")
//            }
//            if(detail_deskripsi.text.isEmpty()){
//                detail_deskripsi.setError("Deskripsi tidak boleh kosong")
//            }
//            if(detail_userId.text.isEmpty()){
//                detail_userId.setError("UserId tidak boleh kosong")
//            }
//
//            studyViewModel.updateStudy(
//                StudiCase(
//                    userId = detail_userId.text.toString(),
//                    id = studyCase!!.id,
//                    title = detail_title.text.toString(),
//                    body = detail_deskripsi.text.toString()
//                )
//            )
//            finish()
//        }
//
//        btn_delete.setOnClickListener {
//            studyViewModel.deleteStudy(
//                StudiCase(
//                    studyCase!!.userId,
//                    studyCase!!.id,
//                    studyCase!!.title,
//                    studyCase!!.body
//                )
//            )
//            finish()
//        }


    }
}