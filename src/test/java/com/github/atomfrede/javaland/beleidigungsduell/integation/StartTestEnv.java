package com.github.atomfrede.javaland.beleidigungsduell.integation;

import com.github.atomfrede.javaland.beleidigungsduell.AbstractJooqTest;
import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsduellApplication;

/**
 * Execute this to start the application with test environment setup.
 *
 * This should be used to develop Karate API much faster.
 * The application keeps running while API tests can be executed
 * directly requesting this application.
 */
public class StartTestEnv extends AbstractJooqTest {
    
     static KarateMockServerExtension losSchimpfosApiMock =
        KarateMockServerExtension.create(KarateIntegrationTest.class,
            "mock/LosSchimpfosApiMock.feature");

    public static void main(String[] args) {

        losSchimpfosApiMock.start();
        System.setProperty("javaland.losshimpfos.api", losSchimpfosApiMock.getBaseUrl() + "/schimpfos-wortos.php");
        BeleidigungsduellApplication.main(args);
    }
}
