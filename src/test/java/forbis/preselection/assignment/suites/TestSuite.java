package forbis.preselection.assignment.suites;

import forbis.preselection.assignment.data.ResultRecordTest;
import forbis.preselection.assignment.data.TokenGroupTest;
import forbis.preselection.assignment.dto.ResultRecordDtoTest;
import forbis.preselection.assignment.utils.TextProcessingUtilTest;
import forbis.preselection.assignment.web.service.MainPageServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ResultRecordTest.class,
        TokenGroupTest.class,
        ResultRecordDtoTest.class,
        TextProcessingUtilTest.class,
        MainPageServiceTest.class
})
public class TestSuite {
}
