package com.entrpn.room.livedata.example.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.entrpn.room.livedata.example.BR
import com.entrpn.room.livedata.example.databinding.PeopleDetailFragmentBinding
import com.entrpn.room.livedata.example.models.PeopleModel

class PeopleDetailFragment : Fragment() {

    /*//////////////////////////////////////////////////////////
    // LIFECYCLE OVERRIDES.
    *///////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val model = arguments.getSerializable(PeopleModel.TAG) as PeopleModel
        val binding = PeopleDetailFragmentBinding.inflate(inflater,container,false)
        binding.setVariable(BR.model,model)
        return binding.root
    }


    /*//////////////////////////////////////////////////////////
    // COMPANION OBJECT
    *///////////////////////////////////////////////////////////
    companion object {
        val TAG = "PeopleDetailFragment"
        fun getInstance(model: PeopleModel?) : Fragment {
            val fragment = PeopleDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable(PeopleModel.TAG,model)
            fragment.arguments = bundle
            return fragment
        }
    }

}