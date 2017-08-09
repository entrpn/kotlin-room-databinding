package com.entrpn.room.livedata.example

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.entrpn.room.livedata.example.adapter.BindingRecyclerViewAdapter
import com.entrpn.room.livedata.example.data.PeopleFactory
import com.entrpn.room.livedata.example.data.PeopleResponse
import com.entrpn.room.livedata.example.db.AppDatabase
import com.entrpn.room.livedata.example.models.PeopleTransactions
import com.entrpn.room.livedata.example.viewmodel.PeopleListViewModel
import kotlinx.android.synthetic.main.people_fragment.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class PeopleFragment: Fragment(){

    /*//////////////////////////////////////////////////////////
    // LIFECYCLE OVERRIDES.
    *///////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syncUI()
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.people_fragment, container, false)
    }


    /*//////////////////////////////////////////////////////////
    // PRIVATE METHODS
    *///////////////////////////////////////////////////////////
    private fun syncUI() {
        btnPopulate.setOnClickListener{
            populateDb()
        }
        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(PeopleListViewModel::class.java)

        borrowedListRecyclerView.setHasFixedSize(true)
        borrowedListRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = BindingRecyclerViewAdapter<PeopleTransactions>(ObservableArrayList(),viewModel)
        borrowedListRecyclerView.adapter = adapter
        val simpleItemTouchCallback = object: ItemTouchHelper.SimpleCallback(0,(ItemTouchHelper.RIGHT.or(ItemTouchHelper.LEFT))) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                val position = viewHolder?.adapterPosition
                position?.let{
                    val model = adapter.mModels[position]
                    async(CommonPool) {
                        AppDatabase.getDatabase(activity)?.peopleModelDao()?.deletePeople(model.peopleModel)
                    }
                }
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(borrowedListRecyclerView)

        viewModel.getPeople()?.observe(activity as LifecycleOwner, Observer {
            t: List<PeopleTransactions>? -> run {
            adapter.addModels(t)
            exportDatabase(AppDatabase.DB_NAME)
        }
        })
    }
    private fun populateDb() {
        PeopleFactory.peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL).enqueue(object: Callback<PeopleResponse> {
            override fun onFailure(call: Call<PeopleResponse>?, t: Throwable?) {
                Toast.makeText(context,"Could not make network call.", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<PeopleResponse>?, response: Response<PeopleResponse>?) {
                if (response != null && response.isSuccessful) {
                    async(CommonPool) {
                        response.body().peopleModelList.forEach {
                            i -> AppDatabase.getDatabase(activity)?.peopleModelDao()?.addPeople(i.getPeopleModel())
                            AppDatabase.getDatabase(activity)?.pictureModelDao()?.addPicture(i.getPictureModel())
                            AppDatabase.getDatabase(activity)?.nameModelDao()?.addName(i.getNameModel())
                            AppDatabase.getDatabase(activity)?.loginModelDao()?.addLogin(i.getLoginModel())
                            AppDatabase.getDatabase(activity)?.locationModelDao()?.addLocation(i.getLocationModel())}
                    }
                }
            }
        })
    }
    private fun exportDatabase(databaseName: String) {
        try {
            val sd = Environment.getExternalStorageDirectory()
            val data = Environment.getDataDirectory()

            if (sd.canWrite()) {
                val currentDBPath = "//data//${activity.packageName}//databases//$databaseName"
                val backupDBPath = "backupname.db"
                val currentDB = File(data, currentDBPath)
                val backupDB = File(sd, backupDBPath)
                Log.i("TESTEST","backupDb: "+backupDB.absoluteFile)

                if (currentDB.exists()) {
                    val src = FileInputStream(currentDB).getChannel()
                    val dst = FileOutputStream(backupDB).getChannel()
                    dst.transferFrom(src, 0, src.size())
                    src.close()
                    dst.close()
                }
            } else {
                Toast.makeText(activity,"Did you accept write sd card permission? ", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Log.e("TESTEST",""+e);
        }

    }


    /*//////////////////////////////////////////////////////////
    // COMPANION OBJECT
    *///////////////////////////////////////////////////////////
    companion object{
        val TAG = "PeopleFragment"
        fun getInstance() : Fragment {
            return PeopleFragment()
        }
    }

}