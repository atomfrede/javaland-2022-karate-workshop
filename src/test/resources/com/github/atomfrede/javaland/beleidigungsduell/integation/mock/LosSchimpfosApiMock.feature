Feature: Los Schimpfos API mock

  Background:
    * def delay = 0
    * def nextStatus = 200
    * def nextBeleidigungDefault = "AbstractGenericIdiot"
    * def nextBeleidigung = nextBeleidigungDefault

  Scenario: methodIs('get') && pathMatches('/schimpfos-wortos.php')
    * def responseStatus = nextStatus
    * def response = nextBeleidigung

  Scenario: methodIs('put') && pathMatches('/_admin/next-beleidigung')
    * def nextBeleidigung = request

    * def responseStatus = 201

  Scenario: methodIs('put') && pathMatches('/_admin/next-status')
    * def nextStatus = request
    * def responseStatus = 201

  Scenario: methodIs('put') && pathMatches('/_admin/reset')
    * def nextStatus = 200
    * def nextBeleidigung = nextBeleidigungDefault
    * def responseStatus = 201

  Scenario:
    * print 'No dedicated rule matches incoming request.'
    * print 'With Headers:'
    * print requestHeaders
    * print 'With Request Parameters'
    * print requestParams
    * print 'And Request:'
    * print request
    * def response = {status: "OK"}
    * def responseStatus = 200
