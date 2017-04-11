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

package com.fepelus.calculator

interface Calculator {
    fun number(i: Double)
    fun digit(i: Int)
    fun decimalPoint()
    fun enter()
    fun add()
    fun multiply()
    fun subtract()
    fun divide()
    fun sign()
    fun swap()
    fun undo()
    fun redo()
    fun power()
    fun delete()
}