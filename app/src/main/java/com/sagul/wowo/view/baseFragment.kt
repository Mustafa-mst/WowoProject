package com.sagul.wowo.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.navigation.Navigation
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.sagul.wowo.R
import com.sagul.wowo.databinding.FragmentBaseBinding


class baseFragment : Fragment() {
private lateinit var binding:FragmentBaseBinding
private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBaseBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        animation(view.context)
        auth= FirebaseAuth.getInstance()
        binding.joinNow.setOnClickListener {
           val action=baseFragmentDirections.actionBaseFragmentToSignInFragment2()
            Navigation.findNavController(view).navigate(action)
        }
        binding.loginText.setOnClickListener {
            val action=baseFragmentDirections.actionBaseFragmentToSignInFragment2(1)
            Navigation.findNavController(view).navigate(action)
        }
        auth.currentUser?.let {
            val action=baseFragmentDirections.actionBaseFragmentToShowFragment()
            Navigation.findNavController(view).navigate(action)
        }


    }






//    fun animation(context:Context){
//        var animation=android.view.animation.AnimationUtils.loadAnimation(context,R.anim.anim).apply {
//            duration=2000
//        }
//        var animation2=android.view.animation.AnimationUtils.loadAnimation(context,R.anim.anim2).apply {
//            duration=2000
//        }
//        binding.imageView.animation=animation
//        binding.letsTxt.animation=animation
//        binding.subTxt.animation=animation
//        binding.joinNow.animation=animation2
//
//
//
//
//    }

}