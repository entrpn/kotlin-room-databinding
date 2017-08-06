package com.entrpn.room.livedata.example.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.entrpn.room.livedata.example.R
import com.entrpn.room.livedata.example.data.PeopleFactory
import com.entrpn.room.livedata.example.db.AppDatabase
import com.entrpn.room.livedata.example.models.PeopleTransactions

class PeopleListViewModel(application: Application) : AndroidViewModel(application), IViewModel {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    val selected: MutableLiveData<PeopleTransactions> = MutableLiveData()
    var peopleModelList: LiveData<List<PeopleTransactions>>? = null


    /*//////////////////////////////////////////////////////////
    // INITIALIZATION
    *///////////////////////////////////////////////////////////
    init {
        loadPeople()
    }


    /*//////////////////////////////////////////////////////////
    // PUBLIC METHODS
    *///////////////////////////////////////////////////////////
    fun getPeople() : LiveData<List<PeopleTransactions>>? {
        return peopleModelList
    }
    fun onItemClick(model: PeopleTransactions) {
        selected.value = model
        selected.postValue(null)
    }
    fun getSelectedModel() : LiveData<PeopleTransactions> {
        return selected
    }


    /*//////////////////////////////////////////////////////////
    // OVERRIDE IVIEWMODEL
    *///////////////////////////////////////////////////////////
    override fun getBinderLayoutId(): Int {
        return R.layout.people_item
    }


    /*//////////////////////////////////////////////////////////
    // PRIVATE METHODS
    *///////////////////////////////////////////////////////////
    private fun loadPeople() {
        peopleModelList = AppDatabase.getDatabase(getApplication())!!.peopleModelDao().getAllPeople()
    }
}