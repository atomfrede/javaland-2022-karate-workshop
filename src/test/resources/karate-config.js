function fn() {

    karate.env = karate.env ? karate.env : 'local';

    karate.log(`Karate environment is: ${karate.env}`);

    var portFor = (name, defaultPort) => {
        var port = karate.properties[name];
        if (!port) {
            port = defaultPort;
        }
        return port;
    }

    var config = {
        ports : {
            bleidigungs_app_port: portFor('bleidigungs_app_port', 8080)
        }
    }

    config.baseUrls = {
        bleidigungs_app: `http://localhost:${config.ports.bleidigungs_app_port}`,
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
