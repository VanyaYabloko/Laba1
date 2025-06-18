package com.example.wordscramblegame

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var scrambledWordTextView: TextView
    private lateinit var guessEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var submitButton: Button

    private val wordList = listOf(
        "apple", "banana", "orange", "grape", "lemon",
        "pear", "kiwi", "mango", "melon", "peach"
    )

    private lateinit var currentWord: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrambledWordTextView = findViewById(R.id.scrambledWordTextView)
        guessEditText = findViewById(R.id.guessEditText)
        resultTextView = findViewById(R.id.resultTextView)
        submitButton = findViewById(R.id.submitButton)

        setupNewWord()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun setupNewWord() {
        currentWord = wordList.random()
        scrambledWordTextView.text = currentWord.toCharArray().apply { shuffle() }.joinToString("")
        guessEditText.text.clear()
        resultTextView.text = ""
    }

    private fun checkAnswer() {
        val userGuess = guessEditText.text.toString().trim()

        if (userGuess.equals(currentWord, ignoreCase = true)) {
            resultTextView.text = getString(R.string.correct)
            setupNewWord()
        } else {
            resultTextView.text = getString(R.string.incorrect)
        }
    }

    fun onKeyPressed(view: View) {
        val button = view as Button
        val letter = button.text.toString()
        guessEditText.append(letter)
    }
}