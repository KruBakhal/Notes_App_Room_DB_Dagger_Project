package com.krunal.notes_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krunal.notes_app.Helper.getDateString
import com.krunal.notes_app.Helper.getTimeString
import com.krunal.notes_app.Intermediate.BaseInterface
import com.krunal.notes_app.R
import com.krunal.notes_app.Model.NotesModel
import java.util.*

class HomeNotesAdapter(
    private var moviesList: List<NotesModel>?,
    var baseInterface: BaseInterface,
) :
    RecyclerView.Adapter<HomeNotesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var notes_time: TextView
        var notes_descriptions: TextView
        var notes_date: TextView

        init {
            title = view.findViewById<View>(R.id.notes_title) as TextView
            notes_descriptions = view.findViewById<View>(R.id.notes_descriptions) as TextView
            notes_date = view.findViewById<View>(R.id.notes_date) as TextView
            notes_time = view.findViewById<View>(R.id.notes_time) as TextView
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val movie: NotesModel?
        movie = moviesList?.get(position)
        holder.title.setText(movie?.title)
        holder.notes_descriptions.setText(movie?.text)
        holder.notes_date.setText(movie?.dateTime?.let { Date().getDateString(it) })
        holder.notes_time.setText(movie?.dateTime?.let { Date().getTimeString(it) })

        holder.itemView.setOnClickListener {
            baseInterface.onCLickItems(position)
        }

    }

    override fun getItemCount(): Int {
        if (moviesList == null || moviesList!!.size == 0)
            return 0
        else
            return moviesList!!.size


    }

    fun setListData(notes: List<NotesModel>) {
        if (moviesList?.size!! >= 0 && moviesList!!.containsAll(notes)) {

        } else {
            moviesList = notes
            notifyDataSetChanged()
        }
    }


}