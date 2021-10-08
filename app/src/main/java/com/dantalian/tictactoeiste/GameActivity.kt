package com.dantalian.tictactoeiste

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.dantalian.tictactoeiste.databinding.ActivityGameBinding
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reset.setOnClickListener {
            reset()
        }

        binding.button1.setOnClickListener { clickFun(binding.button1) }
        binding.button2.setOnClickListener { clickFun(binding.button2) }
        binding.button3.setOnClickListener { clickFun(binding.button3) }
        binding.button4.setOnClickListener { clickFun(binding.button4) }
        binding.button5.setOnClickListener { clickFun(binding.button5) }
        binding.button6.setOnClickListener { clickFun(binding.button6) }
        binding.button7.setOnClickListener { clickFun(binding.button7) }
        binding.button8.setOnClickListener { clickFun(binding.button8) }
        binding.button9.setOnClickListener { clickFun(binding.button9) }
    }


    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptyCell = ArrayList<Int>()
    var activePlayer = 1

    private fun clickFun(view: View){
        val button = view as Button
        var cellId = 0
        when(button){
            binding.button1 -> cellId = 1
            binding.button2 -> cellId = 2
            binding.button3 -> cellId = 3
            binding.button4 -> cellId = 4
            binding.button5 -> cellId = 5
            binding.button6 -> cellId = 6
            binding.button7 -> cellId = 7
            binding.button8 -> cellId = 8
            binding.button9 -> cellId = 9
        }

        play(button, cellId)
    }

    private fun play(button: Button, cellId: Int) {
        if(activePlayer == 1){
            button.text = "X"
            player1.add(cellId)
            button.setTextColor(Color.parseColor("#EC0C0C"))
            emptyCell.add(cellId)
            button.isEnabled = false
            val checkWinner = checkWinner()
            if(checkWinner == 1)
                reset()
            activePlayer = 2
        }

        else{
            button.text = "O"
            player2.add(cellId)
            button.setTextColor(Color.parseColor("#EC0C0C"))
            emptyCell.add(cellId)
            button.isEnabled = false
            val checkWinner = checkWinner()
            if(checkWinner == 1)
                reset()
            activePlayer = 1
        }
    }

    private fun checkWinner(): Int {
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) || (player1.contains(
                1
            ) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) || (player1.contains(
                7
            ) && player1.contains(8) && player1.contains(9)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) || (player1.contains(
                1
            ) && player1.contains(5) && player1.contains(9)) ||
            player1.contains(3) && player1.contains(5) && player1.contains(7) || (player1.contains(2) && player1.contains(
                5
            ) && player1.contains(8))
        ) {
            buttonDisable()
            disableReset()
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 1 has won the game.."+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                reset()
            }
            build.setNegativeButton("Exit") { _, _ ->
                exitProcess(1)

            }
            build.show()
            return 1


        } else if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) || (player2.contains(
                1
            ) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(3) && player2.contains(6) && player2.contains(9)) || (player2.contains(
                7
            ) && player2.contains(8) && player2.contains(9)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) || (player2.contains(
                1
            ) && player2.contains(5) && player2.contains(9)) ||
            player2.contains(3) && player2.contains(5) && player2.contains(7) || (player2.contains(2) && player2.contains(
                5
            ) && player2.contains(8))
        ) {
            buttonDisable()
            disableReset()
            val build = AlertDialog.Builder(this)
            build.setTitle("Game Over")
            build.setMessage("Player 2 have won the game"+"\n\n"+"Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                reset()
            }
            build.setNegativeButton("Exit") { _, _ ->
                exitProcess(1)
            }
            build.show()
            return 1
        } else if (emptyCell.contains(1) && emptyCell.contains(2) && emptyCell.contains(3) && emptyCell.contains(
                4
            ) && emptyCell.contains(5) && emptyCell.contains(6) && emptyCell.contains(7) &&
            emptyCell.contains(8) && emptyCell.contains(9)
        ) {

            val build = AlertDialog.Builder(this)
            build.setTitle("Game Draw")
            build.setMessage("Nobody Wins" + "\n\n" + "Do you want to play again")
            build.setPositiveButton("Ok") { _, _ ->
                reset()
            }
            build.setNegativeButton("Exit") { _, _ ->
                exitProcess(1)
            }
            build.show()
            return 1

        }
        return 0
    }

    private fun disableReset() {
        binding.reset.isEnabled = false
        Handler().postDelayed(Runnable { binding.reset.isEnabled = true }, 2200)
    }

    private fun buttonDisable() {
        for (i in 1..9) {
            val buttonSelected = when (i) {
                1 -> binding.button1
                2 -> binding.button2
                3 -> binding.button3
                4 -> binding.button4
                5 -> binding.button5
                6 -> binding.button6
                7 -> binding.button7
                8 -> binding.button8
                9 -> binding.button9
                else -> binding.button1

            }
            if (buttonSelected.isEnabled)
                buttonSelected.isEnabled = false
        }
    }

    private fun reset() {
        player1.clear()
        player2.clear()
        emptyCell.clear()
        activePlayer = 1;
        for (i in 1..9) {
            var buttonselected: Button? = when (i) {
                1 -> binding.button1
                2 -> binding.button2
                3 -> binding.button3
                4 -> binding.button4
                5 -> binding.button5
                6 -> binding.button6
                7 -> binding.button7
                8 -> binding.button8
                9 -> binding.button9
                else -> binding.button1
            }
            buttonselected?.isEnabled = true
            buttonselected?.text = ""
        }
    }
}