import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
      features = "C:\\Users\\thest\\IdeaProjects\\meetUpsearch\\src\\test\\java\\featureFiles"
//      ,glue="src/test/java/stepFiles"
 //       ,glue={"D:\\testing1\\src\\test\\java\\stepFiles"}

)

public class TestRunner {

}
