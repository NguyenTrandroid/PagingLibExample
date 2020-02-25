package nguyentrandroid.com.paginglibexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import nguyentrandroid.com.paginglibexample.adapter.NotisAdapter


class MainActivity : AppCompatActivity() {
    private val likesAdapter =
        NotisAdapter()
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeLiveData()
        initializeList()



    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        list.adapter = likesAdapter

    }

    private fun observeLiveData() {
        mainViewModel.getLikes().observe(this, Observer {
            Log.d("Size",""+it.size)
            likesAdapter.submitList(it)
        })
    }
}
