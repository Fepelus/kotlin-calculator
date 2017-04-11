/*
RPC Calculator command line client
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

package com.fepelus.calculator.cli;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputTest {
    @Test
    public void shouldBufferAnyUpdates() throws IOException {

        // Given a boundary
        BufferedBoundary boundary = new BufferedBoundary();

        // And a stack with 2.0 at the head
        List<Double> stack = new ArrayList();
        stack.add(2.0);
        stack.add(4.0);
        Assert.assertEquals((Double)2.0, stack.get(0));

        // When I update the boundary with the stack
        boundary.update(stack, "", false);

        // Then the boundary prints the head of the stack
        Assert.assertEquals((Double)2.0, boundary.lastUpdate());
    }

    @Test
    public void shouldUpdateNullIfAnStackIsEmpty() throws IOException {

        // Given a boundary
        BufferedBoundary boundary = new BufferedBoundary();

        // And a stack with 2.0 at the head
        List<Double> stack = new ArrayList();

        // When I update the boundary with the stack
        boundary.update(stack, "", false);

        // Then the boundary prints the head of the stack
        Assert.assertNull(boundary.lastUpdate());
    }


}
