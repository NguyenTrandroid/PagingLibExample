package nguyentrandroid.com.paginglibexample.utils

import androidx.recyclerview.widget.DiffUtil
import nguyentrandroid.com.paginglibexample.models.notis.Hit


class NotisDiffUtilCallBack : DiffUtil.ItemCallback<Hit>() {
    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
        return oldItem == newItem

    }
}