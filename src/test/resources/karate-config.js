function fn() {

    karate.env = karate.env ? karate.env : 'local';

    karate.log(`Karate environment is: ${karate.env}`);

    var getProperty = (name, fallback
    ) => {
        var port = karate.properties[name];
        if (!port) {
            port = fallback
            ;
        }
        return port;
    }

    var config = {
        baseUrls: {
            bleidigungs_app: `http://localhost:${getProperty('bleidigungs_app_port', 8080)}`,
            los_schimpfos_api: "https://los-schimpfos.de",
            los_schimpfos_api_mock: `http://localhost:${getProperty('los_schimpfos_api_mock_port', 8081)}`
        }
    }

    config.uuid = () => {
        return java.util.UUID.randomUUID().toString();
    }

    karate.configure('connectTimeout', 200000);
    karate.configure('readTimeout', 200000);
    karate.configure('retry', {count:3, interval: 3000});

    karate.configure('logPrettyRequest', true);
    karate.configure('logPrettyResponse', true);
    karate.configure('abortedStepsShouldPass', true);
    karate.configure('followRedirects', false);
    karate.configure('report', { showLog: true, showAllSteps: true });

    return config;
}
