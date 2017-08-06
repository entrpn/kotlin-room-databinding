package com.entrpn.room.livedata.example.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.entrpn.room.livedata.example.BR
import com.entrpn.room.livedata.example.viewmodel.IViewModel


/**
 * Created by App Assassins on 7/14/17.
 */

class BindingRecyclerViewAdapter<T>(var mModels: ObservableList<T>, val mViewModel: IViewModel): RecyclerView.Adapter<BindingRecyclerViewAdapter.BindingHolder>(){

    /*/////////////////////////////////////////////////////////////////////////////////////////
    // OVERRIDES.
    *//////////////////////////////////////////////////////////////////////////////////////////
    override fun getItemCount(): Int {
        return mModels.size
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingRecyclerViewAdapter.BindingHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        var viewDataBinding : ViewDataBinding = DataBindingUtil.inflate(inflater, mViewModel.getBinderLayoutId(),parent,false)
        return BindingRecyclerViewAdapter.BindingHolder(viewDataBinding)
    }
    override fun onBindViewHolder(holder: BindingRecyclerViewAdapter.BindingHolder, position: Int) {
        val model = mModels[position]
        holder.mBinding.setVariable(BR.model,model)
        holder.mBinding.setVariable(BR.position,position)
        holder.mBinding.setVariable(BR.viewModel,mViewModel)
        holder.mBinding.executePendingBindings()
    }

    fun addModels(modelList: List<T>?) {
        mModels.clear()
        modelList?.forEach{i -> mModels.add(i)}
        notifyDataSetChanged()
    }

    /*///////////////////////////////////////////////////////////////
    // SCOPED CLASSES
    *////////////////////////////////////////////////////////////////
    class BindingHolder(val mBinding: ViewDataBinding) : RecyclerView.ViewHolder(mBinding.root)

}