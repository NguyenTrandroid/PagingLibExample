package nguyentrandroid.com.paginglibexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_row.view.*
import nguyentrandroid.com.paginglibexample.R
import nguyentrandroid.com.paginglibexample.models.notis.Hit
import nguyentrandroid.com.paginglibexample.utils.NetworkState
import nguyentrandroid.com.paginglibexample.utils.NotisDiffUtilCallBack


class NotisAdapter : PagedListAdapter<Hit, RecyclerView.ViewHolder>((NotisDiffUtilCallBack())) {
    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1
    private val networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_PROGRESS) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(
                view
            )

        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
            return ItemViewHolder(
                view
            )
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {

            getItem(position)?.let {
                if (it._source.iv104.equals("halo_noti_like_post")) {
                    holder.bindPost(it)
                }
            }

        } else {

        }

    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvt = itemView.img_avatar
        val tvTitle = itemView.tv_title
        val tvIcon = itemView.tv_icon

        var layout = itemView.rl_layout

        fun bindPost(hit: Hit) {
            with(hit) {
                tvTitle.text = hit._source.fi101[0].iv102 + " đã bày tỏ cảm xúc về bài viết của bạn"
                tvIcon.text=hit._source.iv107
                Picasso.get()
                    .load(hit._source.fi101[0].iv103)
                    .into(imgAvt)

            }
        }
    }

    class NetworkStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPost(hit: Hit) {
            with(hit) {
                // chua lm dc cai nay

            }
        }

    }


}