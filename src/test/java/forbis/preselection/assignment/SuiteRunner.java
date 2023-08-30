package forbis.preselection.assignment;

import forbis.preselection.assignment.suites.TestSuite;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

@Slf4j
public class SuiteRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);

        printFailures(result.getFailures());
        log.info("Did all tests pass: {}", result.wasSuccessful());
    }

    private static void printFailures(List<Failure> failures) {
        for (Failure failure : failures) {
            System.out.println(failure.toString());
        }
    }
}
