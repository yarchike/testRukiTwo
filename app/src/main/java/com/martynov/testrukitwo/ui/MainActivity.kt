package com.martynov.testrukitwo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.martynov.testrukitwo.R
import com.martynov.testrukitwo.adapter.CellAdapter
import com.martynov.testrukitwo.databinding.ActivityMainBinding
import com.martynov.testrukitwo.model.Cell
import com.martynov.testrukitwo.model.State

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var items = ArrayList<Cell>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        items.add(Cell(State.LIFE))
        items.add(Cell(State.DEAD))
        items.add(Cell(State.DEAD))

        with(binding.container) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CellAdapter(items as MutableList<Cell>)
        }

        binding.buttonCreate.setOnClickListener {
         val cell = deadAndLive()


            if (items.size > 2 && items.get(items.size -1).state == State.LIVE  && items.get(items.size -2).state == State.LIVE  && items.get(items.size -3).state == State.LIVE) {
                  items.add(Cell(State.LIFE))
            }
            if(items.size > 3 && items.get(items.size -1).state == State.DEAD  && items.get(items.size -2).state == State.DEAD  && items.get(items.size -3).state == State.DEAD){
                if(items.get(items.size - 4).state == State.LIFE){
                    items.removeAt(items.size - 4)
                }
            }

            binding.container.adapter?.notifyDataSetChanged()

        }
    }

    private fun deadAndLive(): Cell {
        val random = (0..1).random()
        val cell =
            when (random) {
                0 -> {
                    Cell(State.DEAD)
                }
                else -> {
                    Cell(State.LIVE)
                }
            }
        items.add(cell)
        return cell
    }
}