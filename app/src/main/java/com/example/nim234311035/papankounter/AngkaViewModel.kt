package com.example.nim234311035.papankounter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AngkaViewModel : ViewModel() {
    private val _scoreTeamA = MutableStateFlow(0)
    val scoreTeamA: StateFlow<Int> = _scoreTeamA

    private val _scoreTeamB = MutableStateFlow(0)
    val scoreTeamB: StateFlow<Int> = _scoreTeamB

    private val _gameTeamA = MutableStateFlow(0)
    val gameTeamA: StateFlow<Int> = _gameTeamA

    private val _gameTeamB = MutableStateFlow(0)
    val gameTeamB: StateFlow<Int> = _gameTeamB

    private val winningScore = 21

    fun increaseScoreA() {
        _scoreTeamA.value += 1
        checkGameWinner()
    }

    fun increaseScoreB() {
        _scoreTeamB.value += 1
        checkGameWinner()
    }

    fun decreaseScoreA() {
        if (_scoreTeamA.value > 0) _scoreTeamA.value -= 1
    }

    fun decreaseScoreB() {
        if (_scoreTeamB.value > 0) _scoreTeamB.value -= 1
    }

    private fun checkGameWinner() {
        if (_scoreTeamA.value >= winningScore && _scoreTeamA.value - _scoreTeamB.value >= 2) {
            _gameTeamA.value += 1
            resetScores()
        } else if (_scoreTeamB.value >= winningScore && _scoreTeamB.value - _scoreTeamA.value >= 2) {
            _gameTeamB.value += 1
            resetScores()
        }
    }

    fun resetScores() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
    }

    fun resetGame() {
        _gameTeamA.value = 0
        _gameTeamB.value = 0
        resetScores()
    }

    fun endCurrentGame() {
        when {
            _scoreTeamA.value > _scoreTeamB.value -> _gameTeamA.value += 1
            _scoreTeamB.value > _scoreTeamA.value -> _gameTeamB.value += 1
        }
        resetScores()
    }
}
