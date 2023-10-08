package com.example.truly

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truly.databinding.ActivityHomeBinding
import java.io.Serializable

class HomeActivity : AppCompatActivity(){
    private lateinit var binding : ActivityHomeBinding
    private lateinit var adapter : ChaptersAdapter
    private var data : ArrayList<ChaptersModel> = ArrayList()
    var exp : ArrayList<ExperimentsModel> = ArrayList()
    var exp1: ArrayList<ExperimentsModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exp.add(ExperimentsModel("Exp1",1))
        exp.add(ExperimentsModel("Exp2",2))
        exp.add(ExperimentsModel("Exp4",4))

        exp1.add(ExperimentsModel("Exp1",1))
        exp1.add(ExperimentsModel("Exp2",2))
        exp1.add(ExperimentsModel("Exp3",3))

        data.add(ChaptersModel("Chapter 1: Food Where does it come from?",exp))
        data.add(ChaptersModel("Chapter 2: Components of food",exp1))
        data.add(ChaptersModel("Chapter 3: Fibre to Fabric",exp))

        binding.revVer.layoutManager = LinearLayoutManager(this)
        binding.revVer.setHasFixedSize(true)

        adapter = ChaptersAdapter()

        adapter.setData(this@HomeActivity,data)
        binding.revVer.adapter = adapter

        adapter.setOnClickListener {
            val intent = Intent(this@HomeActivity, MainActivity::class.java)
            intent.putExtra("experiment",it)
            startActivity(intent)
        }
    }
}