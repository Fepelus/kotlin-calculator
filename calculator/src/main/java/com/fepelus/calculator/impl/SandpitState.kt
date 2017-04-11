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

import com.fepelus.calculator.OutputBoundary

class SandpitState(val model: Model, val outputBoundary: OutputBoundary) : State {
    override fun update() = outputBoundary.update(model.stack, model.sandpit, true)
    override fun digit(i: Int): State {
        return SandpitState(
                Model(model.stack, model.sandpit + i.toString()),
                outputBoundary
        )
    }

    override fun decimalPoint(): State {
        if (model.sandpit.contains(".")) {
            return this
        }
        return SandpitState(
                Model(model.stack, model.sandpit + "."),
                outputBoundary
        )
    }

    override fun enter(): State {
        val newstack = Stack(model.stack)
        newstack.push(model.sandpit.toDouble())
        return StackState(Model(newstack, ""), outputBoundary)
    }

    override fun number(d: Double): State {
        val newState = enter()
        return newState.number(d)

    }
    override fun add(): State {
        val newState = enter()
        return newState.add()
    }

    override fun multiply(): State {
        val newState = enter()
        return newState.multiply()
    }

    override fun subtract(): State {
        val newState = enter()
        return newState.subtract()
    }

    override fun divide(): State {
        val newState = enter()
        return newState.divide()
    }

    override fun sign(): State {
        if (model.sandpit.startsWith("-")) {
            return SandpitState(Model(model.stack, model.sandpit.substring(1)), outputBoundary)
        }
        return SandpitState(Model(model.stack, "-" + model.sandpit), outputBoundary)
    }

    override fun power(): State {
        val newState = enter()
        return newState.power()
    }

    override fun swap(): State {
        val newState = enter()
        return newState.swap()
    }

    override fun delete(): State {
        assert(model.sandpit.length > 0)

        if (model.sandpit.length == 1) {
            return StackState(Model(model.stack, ""), outputBoundary)
        }
        return SandpitState(
                Model(model.stack, model.sandpit.substring(0, model.sandpit.length - 1)),
                outputBoundary
        )
    }

}
