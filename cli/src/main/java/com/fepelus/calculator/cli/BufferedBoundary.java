package com.fepelus.calculator.cli;

import com.fepelus.calculator.OutputBoundary;

import org.jetbrains.annotations.NotNull;

import java.util.List;

class BufferedBoundary implements OutputBoundary {
    private Double mostRecentStackTop;
    @Override
    public void update(@NotNull List<Double> stack, @NotNull String sandpit, boolean inSandpit) {
        if (stack.size() > 0) {
            mostRecentStackTop = stack.get(0);
        }
    }

    Double lastUpdate() {
        return mostRecentStackTop;
    }
}
