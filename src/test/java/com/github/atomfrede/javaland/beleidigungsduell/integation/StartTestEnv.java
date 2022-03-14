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

    public static void main(String[] args) {

        BeleidigungsduellApplication.main(args);
    }

}
