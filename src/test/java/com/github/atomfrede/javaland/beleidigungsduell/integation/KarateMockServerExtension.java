package com.github.atomfrede.javaland.beleidigungsduell.integation;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.GenericContainer;

import com.intuit.karate.core.MockServer;
import com.intuit.karate.core.MockServer.Builder;
import com.intuit.karate.resource.ResourceUtils;

/**
 * This class is currently not used and we don't know if we will use karate as a lib to implement mocks.
 *
 * Keep it here until the decision is made.
 */
@SuppressWarnings("unused")
public class KarateMockServerExtension implements Extension, BeforeAllCallback, AfterAllCallback {

    private static final Logger logger = LoggerFactory.getLogger(KarateMockServerExtension.class);

    private final Builder mockServerBuilder;

    private MockServer mockServer;

    public KarateMockServerExtension(Builder mockServerBuilder) {
        this.mockServerBuilder = mockServerBuilder;
    }

    /**
     * Create a mock server. The <code>relativePath</code> must be relative to the <code>relativeClass</code>.
     * @param relativeClass starting point to look up the feature file.
     * @param relativePath relative path to the mock feature file. The file suffix (most likely <code>.feature</code>) is required.
     * @return
     */
    public static KarateMockServerExtension create(Class<?> relativeClass, String relativePath) {
        final File mockFile = ResourceUtils.getFileRelativeTo(relativeClass, relativePath);
        return new KarateMockServerExtension(MockServer.feature(mockFile));
    }


    /**
     * Create mock server using an absolute path to the mock feature file.
     * Relative path isn't supported. Use {@link KarateMockServerExtension#create(Class, String)} instead.
     *
     * @param featurePath absolute path or `classpath:` prefixed path to the feature file. Relative path isn't supported
     * @return
     */
    public static KarateMockServerExtension create(String featurePath) {
        return new KarateMockServerExtension(MockServer.feature(featurePath));
    }

    public static KarateMockServerExtension create(File featureFile) {
        return new KarateMockServerExtension(MockServer.feature(featureFile));
    }

    public KarateMockServerExtension httpPort(int value) {

        mockServerBuilder.http(value);
        return this;
    }

    public KarateMockServerExtension httpsPort(int value) {

        mockServerBuilder.https(value);
        return this;
    }

    public KarateMockServerExtension reloadOnChange() {

        mockServerBuilder.watch(true);
        return this;
    }

    public KarateMockServerExtension certFile(File value) {

        mockServerBuilder.certFile(value);
        return this;
    }

    public KarateMockServerExtension keyFile(File value) {

        mockServerBuilder.keyFile(value);
        return this;
    }

    public KarateMockServerExtension args(Map<String, Object> value) {

        mockServerBuilder.args(value);
        return this;
    }

    public KarateMockServerExtension arg(String name, Object value) {

        mockServerBuilder.arg(name, value);
        return this;
    }

    public String getTestContainerBaseUrl() {
        Testcontainers.exposeHostPorts(this.mockServer.getPort());
        return GenericContainer.INTERNAL_HOST_HOSTNAME + ":" + this.mockServer.getPort();
    }


    public String getBaseUrl() {

        return "http://localhost:" + this.mockServer.getPort();
    }


    public int getPort() {

        if (mockServer != null) {
            return mockServer.getPort();
        }
        throw new IllegalStateException("Mock server not started yet!");
    }

    public void start() {

        mockServer = mockServerBuilder.build();
        Testcontainers.exposeHostPorts(mockServer.getPort());
        logger.info("Karate mock server started on port:{}", getPort());
    }

    public void stop() {

        if (mockServer != null) {
            mockServer.stop();
            logger.info("Karate mock server stopped!");
        }
    }

    @Override public void beforeAll(ExtensionContext context) throws Exception {

        start();
    }

    @Override public void afterAll(ExtensionContext context) throws Exception {

        stop();
    }
}
