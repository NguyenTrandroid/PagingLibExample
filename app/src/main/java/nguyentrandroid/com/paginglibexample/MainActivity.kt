package nguyentrandroid.com.paginglibexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
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
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = likesAdapter

    }

    private fun observeLiveData() {
        mainViewModel.getLikes().observe(this, Observer {
            likesAdapter.submitList(it)
        })
    }
}
