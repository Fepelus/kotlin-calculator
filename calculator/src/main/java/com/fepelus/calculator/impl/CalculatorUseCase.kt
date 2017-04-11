/*
RPC Calculator library
Copyright (C) 2017 Patrick Borgeest

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.fepelus.calculator.impl

import com.fepelus.calculator.Calculator
import com.fepelus.calculator.OutputBoundary

class CalculatorUseCase(val outputBoundary: OutputBoundary) : Calculator {
    val undoStack: MutableList<State> = mutableListOf()
    val redoStack: MutableList<State> = mutableListOf()
    var state: State = StackState(Model(listOf(), ""), outputBoundary)

    init {
        state.update()
    }

    fun newState(newstate: State) {
        if (state == newstate) {
            return
        }
        undoStack.add(0, state)
        redoStack.clear()
        state = newstate
        state.update()
    }

    override fun undo() {
        redoStack.add(0, state)
        state = undoStack.removeAt(0)
        state.update()
    }

    override fun redo() {
        undoStack.add(0, state)
        state = redoStack.removeAt(0)
        state.update()
    }

    override fun number(i: Double) = newState(state.number(i))
    override fun digit(i: Int) = newState(state.digit(i))
    override fun decimalPoint() = newState(state.decimalPoint())
    override fun enter() = newState(state.enter())
    override fun add() = newState(state.add())
    override fun multiply() = newState(state.multiply())
    override fun subtract() = newState(state.subtract())
    override fun divide() = newState(state.divide())
    override fun sign() = newState(state.sign())
    override fun swap() = newState(state.swap())
    override fun power() = newState(state.power())
    override fun delete() = newState(state.delete())

}


