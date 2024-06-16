package com.example.tictactoe

//importing

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() { //main activity class

   private lateinit var timer: CountDownTimer //variable for countdown timer

     override fun onCreate(savedInstanceState: Bundle?) { //onCreate function, which is first run when the app is created
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)




        turnTextView = findViewById(R.id.turnTextView) as TextView

         // X & O scores

        xScore = findViewById(R.id.xScore) as TextView
         turnTextView3 = findViewById(R.id.oscore) as TextView



         TimerText = findViewById(R.id.TimerText) as TextView //text that dispalys the timer seconds remaining


        tableLayout = findViewById(R.id.table_Layout) as TableLayout
        resetButton = findViewById(R.id.resetButton) as android.widget.Button


         // new game is started when reset button is clicked, timer is reset
        resetButton!!.setOnClickListener() {
            startNewGame( setClickListener = false)
            timer.start()
        }

        startNewGame(setClickListener = true) //calling start new game function once the app is run

         //getting Best of three option - taken from the options menu and applying to invisible textbox
         var intent = intent
         val name = intent.getIntExtra("Name", 0)
         val timer11 = intent.getIntExtra("Timer", 0)
         val theme = intent.getIntExtra("Theme", 0)

         val view10 = findViewById<View>(R.id.view10);
         if (theme == 1){

             view10.setBackgroundColor(0xFFF0DEFF.toInt())
         }
         if (theme == 0){

             view10.setBackgroundColor(0xFFDEE6FF.toInt())
         }




     //    val result = findViewById<TextView>(R.id.turnTextView4)
       //  result.text = "BO3: " + name

    //     val result2 = findViewById<TextView>(R.id.turnTextView5)
       //  result2.text = "Timer: " + timer


// TIMER
        timer = object : CountDownTimer(11000, 1000){
            override fun onTick(remaining: Long) {
                TimerText?.setText("seconds remaining: " + remaining / 1000)
            }

            override fun onFinish() {
                if(timer11 == 1){ //If timer = 1 (selected from options, then below is true and user has timer in their game)
                turn = if ('X' == turn) 'O' else 'X'
                turnTextView?.text = String.format(resources.getString(R.string.turn), turn)
                checkGameStatus()
                timer.start()
}


        }




    }
     }
    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }


    //defining variables

    var gameBoard: Array<CharArray> = Array(size = 4) { CharArray(size = 4) } //array size of game board. Size is set to 4 as it is a 4x4 grid

    var turn = 'X' //X's turn - which will be the player that goes first once the game is started

    var tableLayout: android.widget.TableLayout? = null
    var turnTextView: android.widget.TextView? = null

    var xScore: android.widget.TextView? = null
    var turnTextView3: android.widget.TextView? = null
    var resetButton: android.widget.Button? = null


    var TimerText: android.widget.TextView? = null


    //xcount & ocount are the variables for the scores
    var Xcount=0
    var Ocount=0



    //START NEW GAME

    private fun startNewGame(setClickListener: Boolean) { //start new game function
        var state: String? = null //defining state


        //   CHANGE COLOUR OF BACKGROUND !!






        //getting Best of three option - taken from the options menu and applying to invisible textbox
        var intent = intent
        val bo3option1 = intent.getIntExtra("Name", 0)
        val timer = intent.getIntExtra("Timer", 0)

  //      val result = findViewById<TextView>(R.id.turnTextView4)
    //    result.text = "BO3: " + name

      //  val result2 = findViewById<TextView>(R.id.turnTextView5)
        //result2.text = "Timer: " + timer

        if( timer == 0){
            TimerText?.isVisible = false //invisible if the user does not want a timer

        }

 //       if(timer == 1){
   //         val timerobject = object : CountDownTimer(30000, 1000) {

  //              override fun onTick(millisUntilFinished: Long) {
  //                  TimerText?.setText("seconds remaining: " + millisUntilFinished / 1000)
 //               }

  //              override fun onFinish() {
  //                  TimerText?.setText("done!")

 //               }


 //           }.start()

 //       }
  //      if (timer == 0){
  //          TimerText?.setText("")
 //       }



          //name variable is the
        if(bo3option1 == 1) { //IF BEST OF 3 HAS BEEN CHOSEN IN OPTIONS, THEN APPLY THE IF STATEMENTS BELOW.


            if (Ocount == 3) { //if score reaches 3, then below is true

                state = String.format(resources.getString(R.string.BO3winner), 'O')
                Log.d("MainActivity", "This is a debug message");
                turnTextView?.text = state
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder.setMessage(state) //display message box, user then clicks "ok"
                builder.setPositiveButton(android.R.string.ok, { dialog, id ->
                    startNewBO3(setClickListener = false) //start new game
                })
                val dialog = builder.create()
                dialog.show()

            }
            if (Xcount == 3) {

                state = String.format(resources.getString(R.string.BO3winner), 'X')
                Log.d("MainActivity", "This is a debug message");
                turnTextView?.text = state
                val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                builder.setMessage(state)
                builder.setPositiveButton(android.R.string.ok, { dialog, id ->
                    startNewBO3(setClickListener = false)
                })
                val dialog = builder.create()
                dialog.show()

            }
        }
        xScore?.text = String.format("X's Score: $Xcount")
        turnTextView3?.text = String.format("O's Score: $Ocount")
        turn = 'X' //Sets the turn to X at start of game

        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)



        for (i in 0 until gameBoard.size) { //gameboard.size taken from array of 4


            for (j in 0 until gameBoard[i].size) {


                gameBoard[i][j] = ' '

                val cell = (tableLayout?.getChildAt(i) as

                        android.widget.TableRow).getChildAt(j) as android.widget.TextView

                cell.text = "" //remove the values from the cells


                if (setClickListener) {
                    cell.setOnClickListener { cellClickListener(i, j) }

                }
            }

        }


    }


    private fun cellClickListener(row: Int, column: Int) { //once a cell is clicked, this function  is called
        if (gameBoard[row][column] == ' ') {
            gameBoard[row][column] = turn

            ((tableLayout?.getChildAt(row) as android.widget.TableRow).getChildAt(column) as
                    TextView).text = turn.toString()

            turn = if ('X' == turn) 'O' else 'X'
            turnTextView?.text = String.format(resources.getString(R.string.turn), turn) //change turn of player
            checkGameStatus()


            turnTextView3?.text = String.format("O's Score: $Ocount")
            xScore?.text = String.format("X's Score: $Xcount")
            timer.start()
        }

    }

    private fun isBoardFull(gameBoard: Array<CharArray>): Boolean { //checks if the game board is full, it checks for draws
        for (i in 0 until gameBoard.size) {
            for (j in 0 until gameBoard[i].size) {
                if (gameBoard[i][j] == ' ') {
                    return false
                }
            }
        }

        return true

    }


    private fun isWinner(gameBoard: Array<CharArray>, w: Char): Boolean { //checks if there is a winner in the game
        for (i in 0 until gameBoard.size) {

            //horizontal wins

            if (gameBoard[i][0] == w && gameBoard[i][1] == w && gameBoard[i][2] == w && gameBoard[i][3] == w) {

                return true //return true if won
                timer.start()

            }
            //vertical wins
            if (gameBoard[0][i] == w && gameBoard[1][i] == w && gameBoard[2][i] == w && gameBoard[3][i] == w){

                return true
                timer.start()
            }

        }
        //diagonal wins
        if ((gameBoard)[0][0] == w && gameBoard[1][1] == w && gameBoard[2][2] == w && gameBoard[3][3] == w ||
            (gameBoard[0][3] == w && gameBoard[1][2] == w && gameBoard[2][1] == w && gameBoard[3][0] == w)) {

            return true
            timer.start()

        }
        return false
    }




private fun checkGameStatus() {

//checks the game status, checks if there is a winner or a draw
    var state: String? = null


   if(isWinner(gameBoard, 'X')){ //if conditions for each winner

    Xcount++

       state = String.format(resources.getString(R.string.winner), 'X')
       timer.start()

    } else  if(isWinner(gameBoard, 'O')){
       Ocount++
       state = String.format(resources.getString(R.string.winner), 'O')
       timer.start()
    } else{
        if (isBoardFull(gameBoard)) {
            state = resources.getString(R.string.draw) //gets draw message
        }
       else{

        }
    }

    if (state != null) {
        turnTextView?.text = state
        val builder = androidx.appcompat.app.AlertDialog.Builder( this)
        builder.setMessage(state)
        builder.setPositiveButton(android.R.string.ok, {dialog, id -> startNewGame( setClickListener = false)
        })
        val dialog = builder.create()
        dialog.show()
    }




}

//check for best of 3 winner

    private fun startNewBO3(setClickListener: Boolean) { //start new game function for best of 3. Resets scores
        var state: String? = null

        timer.start()
        Ocount = 0
        Xcount = 0
        xScore?.text = String.format("X's Score: $Xcount")
        turnTextView3?.text = String.format("O's Score: $Ocount")
        turn = 'X' //Sets the turn to X at start of game

        turnTextView?.text = String.format(resources.getString(R.string.turn), turn)




        for (i in 0 until gameBoard.size) {


            for (j in 0 until gameBoard[i].size) {


                gameBoard[i][j] = ' '

                val cell = (tableLayout?.getChildAt(i) as

                        android.widget.TableRow).getChildAt(j) as android.widget.TextView

                cell.text = ""


                if (setClickListener) {
                    cell.setOnClickListener { cellClickListener(i, j) }

                }
            }
        }
    }



}