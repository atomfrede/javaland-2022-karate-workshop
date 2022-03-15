package com.github.atomfrede.javaland.beleidigungsduell.integation;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.intuit.karate.junit5.Karate;

/**
 *
 * @author Peter Quiel
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class KarateIntegrationTest {

    @RegisterExtension static KarateMockServerExtension losSchimpfosApiMock =
        KarateMockServerExtension.create(KarateIntegrationTest.class, "mock/LosSchimpfosApiMock.feature");

    @LocalServerPort
    private int bleidigungsAppPort;

    @Karate.Test
    public Karate runTestSuite() {

        final Karate runner = Karate.run(
                "AdminApi.feature"
            )
            .systemProperty("bleidigungs_app_port", bleidigungsAppPort + "")
            .systemProperty("los_schimpfos_api_mock_port", losSchimpfosApiMock.getPort() + "")
            //             Ignore every Scenario tagged with @Ignore
            .tags("~@ignore", "~@Ignore");

        runner.relativeTo(getClass());
        return runner;

    }
}
