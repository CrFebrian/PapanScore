package com.example.nim234311035.papankounter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AngkaViewModel : ViewModel() {
    private val _scoreTeamA = MutableStateFlow(0)
    val scoreTeamA: StateFlow<Int> = _scoreTeamA

    private val _scoreTeamB = MutableStateFlow(0)
    val scoreTeamB: StateFlow<Int> = _scoreTeamB

    fun increaseScoreA() {
        _scoreTeamA.value += 1
    }

    fun decreaseScoreA() {
        if (_scoreTeamA.value > 0) _scoreTeamA.value -= 1
    }

    fun increaseScoreB() {
        _scoreTeamB.value += 1
    }

    fun decreaseScoreB() {
        if (_scoreTeamB.value > 0) _scoreTeamB.value -= 1
    }

    fun resetScores() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
    }
}