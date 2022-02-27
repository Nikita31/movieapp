package com.example.phonepemoviesapp.view

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.phonepemoviesapp.model.Results
import com.bumptech.glide.Glide
import android.widget.Button
import com.bumptech.glide.request.RequestOptions
import com.example.phonepemoviesapp.util.MainConstant
import android.widget.LinearLayout

import com.google.android.material.bottomsheet.BottomSheetDialog


class MovieViewHolder internal constructor(private val mViewDataBinding: ViewDataBinding) :

    RecyclerView.ViewHolder(mViewDataBinding.root) {
    fun bind(data: Results?, viewHolder: MovieViewHolder) {
        mViewDataBinding.setVariable(BR.item, data)
        setImage(viewHolder.image, data?.backdropPath)
        inflateBottomSheet(viewHolder.starPlayList)
        mViewDataBinding.executePendingBindings()
    }

    private fun inflateBottomSheet(playList: ImageView) {
        playList.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(playList.context)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)

            val rv :RecyclerView = bottomSheetDialog.findViewById<LinearLayout>(R.id.recyclerview)
            val addToPlaylist : Button = bottomSheetDialog.findViewById<LinearLayout>(R.id.AddToPlaylist)
            setRecycelrView(rv)
            addToPlaylist.setOnClickListener {
                addtoPlaylist()
            }

            bottomSheetDialog.show()
        }



    }

    //populate a sheet to enter playlist name and save in pref or DB and fetch while showing movies list
    private fun addtoPlaylist() {

    }

    //fetch the names of playlist that are created and show then
    //create a new adapter and set the data
    private fun setRecycelrView(rv: RecyclerView) {
        TODO("Not yet implemented")
    }

    //set movie image url
    private fun setImage(image: ImageView, backdropPath: String?) {
        val url  = MainConstant.basePathImage + backdropPath
        val options: RequestOptions = RequestOptions()
            .centerCrop()
        Glide.with(image.context).load(url).apply(options).into(image)
    }
}