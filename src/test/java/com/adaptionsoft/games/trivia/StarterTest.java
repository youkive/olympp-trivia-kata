package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StarterTest {

    @Test
    void should_print_golden_source() {
        ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(testOutput);

        GameRunner.run(out,4, 8);

        Approvals.verify(testOutput.toString());
    }
}
