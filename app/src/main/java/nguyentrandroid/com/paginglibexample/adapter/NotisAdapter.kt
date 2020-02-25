package nguyentrandroid.com.paginglibexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_row.view.*
import nguyentrandroid.com.paginglibexample.R
import nguyentrandroid.com.paginglibexample.models.notis.Hit
import nguyentrandroid.com.paginglibexample.utils.NetworkState
import nguyentrandroid.com.paginglibexample.utils.NotisDiffUtilCallBack


class NotisAdapter : PagedListAdapter<Hit, NotisAdapter.ViewHolder>((NotisDiffUtilCallBack())) {
    private val TYPE_PROGRESS = 0
    private val TYPE_ITEM = 1
    private val networkState: NetworkState? = null
    override fun submitList(pagedList: PagedList<Hit>?) {
        var pagedListTemp:PagedList<Hit>? = pagedList
        Log.d("Size",""+pagedList?.size)

        pagedList?.forEach {
            if(!it._source.iv104.equals("halo_noti_like_post")){
                pagedListTemp?.remove(it)
            }
        }
        Log.d("Size",""+pagedListTemp?.size)


        super.submitList(pagedListTemp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotisAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent, false)
        return ViewHolder(view)

    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvt = itemView.img_avatar
        val tvTitle = itemView.tv_title
        val tvIcon = itemView.tv_icon
        var layout = itemView.rl_layout
        fun bindPost(hit: Hit) {
            with(hit) {
                if(hit._source.iv104.equals("halo_noti_like_post")){
                    tvTitle.text = hit._source.fi101[0].iv102 + " đã bày tỏ cảm xúc về bài viết của bạn"
                    tvIcon.text = hit._source.iv107
                    Picasso.get()
                        .load(hit._source.fi101[0].iv103)
                        .into(imgAvt)
                }else{
                    layout.visibility=View.GONE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

}