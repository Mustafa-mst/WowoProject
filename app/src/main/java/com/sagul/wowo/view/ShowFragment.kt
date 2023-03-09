package com.sagul.wowo.view

import android.graphics.ColorSpace.Model
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.sagul.wowo.R
import com.sagul.wowo.adapter.recyclerAdapter
import com.sagul.wowo.databinding.FragmentShowBinding
import java.util.*
import kotlin.collections.ArrayList


class ShowFragment : Fragment() {
    private lateinit var database:FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var binding:FragmentShowBinding
    private  var listem=ArrayList<com.sagul.wowo.adapter.Model>()
    private var adapter:recyclerAdapter= recyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database= FirebaseFirestore.getInstance()
        storage= FirebaseStorage.getInstance()
        binding.recyclerView.layoutManager=LinearLayoutManager(view.context)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(view.context,DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter=adapter
        getAll()
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               listem.filter {
                   it.countryName.lowercase(Locale.ROOT).contains(newText!!.toLowerCase())
               }.also {
                   adapter.refresh(ArrayList<com.sagul.wowo.adapter.Model>(it))
               }
                return true
            }
        })

    }
    fun getAll(){
        database.collection("Country").orderBy("country",Query.Direction.ASCENDING).get().addOnSuccessListener {
            if (!it.isEmpty&&it!=null){
                listem.clear()
                it.documents.forEach {
                    listem.add(com.sagul.wowo.adapter.Model(it.get("url")as String,it.get("country")as String,it.get("money")as String,it.get("otel")as String,it.get("trans")as String,it.get("food")as String,it.get("weather")as String))
                }
                adapter.refresh(listem)
            }
        }
    }


/* private fun putAll(Urlway:String,countryName:String,money:String,otel:String,trans:String,food:String,weather:String){
        var referance=storage.reference.child("countries/${Urlway}.png")
        referance.downloadUrl.addOnSuccessListener {
            val url=it.toString()
            val hashList= hashMapOf<String,String>("url" to url,"country" to countryName,"money" to money,"otel" to otel,"trans" to trans,"food" to food,"weather" to weather )
            database.collection("Country").add(hashList)
        }.addOnFailureListener {
            Toast.makeText(this.context,it.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }
    //        putAll("england","İngiltere","Pound","Booking","Taksi","Fish And Chips","Kapalı")
//        putAll("india","Hindistan","Rupi","Airbnb","Tuktuk","Masala","Tozlu")
//        putAll("italy","İtalya","Euro","Airbnb","IT Taxi","Pizza","Güneşli")
//        putAll("poland","Polonya","Zloti","Hostels","Bolt","Pierogi","Kapalı")
//        putAll("russia","Rusya","Ruble","Momondo","Yandex.Taxi","Piroşki","Kapalı")
//        putAll("spain","İspanya","Euro","Booking","Uber","Paella","Açık")
//        putAll("thailand","Tayland","Baht","Airbnb","Grab","Pad Thai","Açık")
//        putAll("turkey","Türkiye","Türk Lirası","Airbnb","Bi Taksi","Kebap","Açık")
//        putAll("usa","Amerika Birleşik Devletleri","Dolar","Booking","Lyft","Hamburger","Açık")
    */


}