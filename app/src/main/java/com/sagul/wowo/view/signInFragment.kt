package com.sagul.wowo.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.sagul.wowo.R
import com.sagul.wowo.databinding.FragmentSignInBinding


class signInFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
   private lateinit var binding:FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSignInBinding.inflate(LayoutInflater.from(context),container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= FirebaseAuth.getInstance()
        var animation2=android.view.animation.AnimationUtils.loadAnimation(context,R.anim.anim2).apply {
            duration=2000
        }

        arguments?.let {
            val id=signInFragmentArgs.fromBundle(it).id
            if (id==0){
                binding.letsTxt.text="Hi,there"
                binding.subText.text="Create an account and join us"
                binding.againSignPassword.visibility=View.VISIBLE
                binding.againPasswordTxt.visibility=View.VISIBLE
                binding.signBtn.text="Sign Up"
                binding.mapGors.visibility=View.GONE
                binding.signBtn.setOnClickListener {
                    Save(it)

                }
            }else{
                binding.signBtn.setOnClickListener {
                    LogIn(it)

                }
            }
        }

    }
    private fun Save(view:View){
        var password=binding.passwordTxt
        var secondPassword=binding.againSignPassword
        if (!password.text.toString().equals(secondPassword.text.toString())){
            Snackbar.make(view,"Passwords must be the same",Snackbar.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(binding.emailTxt.text.toString(),binding.passwordTxt.text.toString()).addOnCompleteListener {
                val action=signInFragmentDirections.actionSignInFragmentToShowFragment()
                Navigation.findNavController(view).navigate(action)

            }.addOnFailureListener {
                Snackbar.make(view,it.localizedMessage,Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun LogIn(view:View) {
        if (!binding.emailTxt.text.isEmpty() && !binding.passwordTxt.text.isEmpty()&&!binding.emailTxt.text.isEmpty()) {
            auth.signInWithEmailAndPassword(
                binding.emailTxt.text.toString(),
                binding.passwordTxt.text.toString()
            ).addOnSuccessListener {
                var action = signInFragmentDirections.actionSignInFragmentToShowFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener {
                Snackbar.make(view, it.localizedMessage, Snackbar.LENGTH_LONG).show()
            }

        }else{
            Snackbar.make(view, "Please do not leave the fields blank", Snackbar.LENGTH_LONG).show()
        }
    }



}