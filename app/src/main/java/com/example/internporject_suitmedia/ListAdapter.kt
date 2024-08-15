package com.example.internporject_suitmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(private val listReview: ArrayList<Item>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listReview[position])
    }

    override fun getItemCount(): Int = listReview.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tvUsername : TextView = view.findViewById(R.id.tvUsername)
        private var tvEmail : TextView = view.findViewById(R.id.tvEmail)
        private var ivAvatar : ImageView = view.findViewById(R.id.iv_avatar)

        @SuppressLint("SetTextI18n")
        fun bind(item : Item){
            Glide.with(itemView.context)
                .load(item.avatar)
                .circleCrop()
                .into(ivAvatar)

            tvUsername.text = "${item.firstName} ${item.lastName}"
            tvEmail.text = item.email

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                intent.putExtra("Username", "${item.firstName} ${item.lastName}")
                itemView.context.startActivity(intent)
            }
        }
    }
}