package com.example.studicase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.studicase.R
import com.example.studicase.activies.DetailStudyCase
import com.example.studicase.model.StudiCase
import kotlinx.android.synthetic.main.activity_detail_study_case.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class AdapterClass(private var listData: List<StudiCase>): RecyclerView.Adapter<AdapterClass.ListViewHolder>() {

    fun setNotes(list: List<StudiCase>) {
        this.listData = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: StudiCase) {
            with(itemView){

                var namaDepan : String

                tvUserId.text = data.userId
                tvTitle.setText(data.title)
                tvDesc.setText(data.body)

                val nama: String = data.title.toString()
                if (!nama.isEmpty()) {
                    namaDepan = nama.substring(0, 1)
                } else {
                    namaDepan = " "
                }

                val generator = ColorGenerator.MATERIAL
                val color = generator.randomColor

                val drawable = TextDrawable.builder().buildRound(namaDepan, color)
                imgColor.setImageDrawable(drawable)

                itemView.setOnClickListener {
                    val intent = Intent(context, DetailStudyCase::class.java)
                    intent.putExtra(DetailStudyCase.EXTRA_DETAIL, data)
                    context.startActivity(intent)
                }

            }
        }
    }

}