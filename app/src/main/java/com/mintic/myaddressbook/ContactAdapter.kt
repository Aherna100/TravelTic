package com.mintic.myaddressbook

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList


class ContactAdapter(
    private val mItems: ArrayList<Contact>,
    private val context: Context,
    private val onClick: (Contact?) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = mItems[position]
        holderContact.bind(contact = contact)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameLabel: TextView = itemView.findViewById(R.id.textview_name)
        private var descriptionlabel: TextView = itemView.findViewById(R.id.textview_email)
        private var pointsLabel: TextView = itemView.findViewById(R.id.textview_points)
        private var imageView: ImageView = itemView.findViewById(R.id.imageview_thumb)
        private var currentContact: Contact? = null

        init {
            itemView.setOnClickListener {
                onClick(currentContact)
            }
        }

        fun bind(contact: Contact) {
            currentContact = contact

            nameLabel.text = contact.name
            descriptionlabel.text = contact.description
            pointsLabel.text = contact.points

            Glide.with(context)
                .load(contact.image)
                .into(imageView)
        }
    }

    companion object {
        private const val TAG = "ContactAdapter"
    }
}
