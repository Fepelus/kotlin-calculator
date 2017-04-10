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
