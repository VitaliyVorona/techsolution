import com.github.tomakehurst.wiremock.junit.WireMockRule
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.ClassRule
import org.junit.runner.RunWith

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "classpath:steps",
        format = ["pretty", "html:target/cucumber"]
)

class RunnerClass {

    @ClassRule
    public static WireMockRule wireMockService = new WireMockRule(options().port(8089).httpsPort(8090))

}

