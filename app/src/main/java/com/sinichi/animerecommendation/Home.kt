package com.sinichi.animerecommendation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {
    lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var dataList: ArrayList<Model>
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var frameLayout: FrameLayout
    private lateinit var mFirebaseDatabaseReference: FirebaseDatabase
    private lateinit var animeRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progress_circular)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
        frameLayout = view.findViewById(R.id.frame_layout)

        loadData()
        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    private fun loadData() {
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance()
        animeRef = mFirebaseDatabaseReference.reference.child("list_anime")
        val valueListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Snackbar.make(frameLayout, "Connection Error, please exit", Snackbar.LENGTH_LONG)
                    .show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                dataList = ArrayList()
                progressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                for (snapshot in p0.children) {
                    val model = snapshot.getValue(Model::class.java)
                    model?.id  = snapshot.key.toString()
                    dataList.add(model!!)
                }
                val adapter = RecyclerViewAdapter(dataList)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter.notifyDataSetChanged()
            }
        }
        animeRef.addValueEventListener(valueListener)
    }

}
