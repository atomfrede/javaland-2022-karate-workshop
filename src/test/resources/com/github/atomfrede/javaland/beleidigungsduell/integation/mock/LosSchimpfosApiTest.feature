Feature: Los Schimpfos API

  Background:
    * def start = () => karate.start('LosSchimpfosApiMock.feature').port
    * def port = callonce start

    * def apiBaseUrl = baseUrls.los_schimpfos_api
    * def mockBaseUrl = 'http://localhost:' + port

  Scenario: Test Get Beleidigung API
    * call read('LosSchimpfosApiContract.feature') {baseUrl: #(apiBaseUrl)}

  Scenario: Test Get Beleidigung Mock
    * call read('LosSchimpfosApiContract.feature') {baseUrl: #(mockBaseUrl)}

  Scenario: Test set next Beleidigung
    * url mockBaseUrl
    * path '_admin', 'next-beleidigung'
    * request 'ForSchleifenSchreiber'
    * method put
    * status 201

    * def response = call read('LosSchimpfosApiContract.feature') {baseUrl: #(mockBaseUrl)}
    * match response.beleidigung == 'ForSchleifenSchreiber'

    Scenario: Test set next status and reset next status
      * url mockBaseUrl
      * path '_admin', 'next-status'
      * request 404
      * method put
      * status 201

      * path 'schimpfos-wortos.php'
      * method get
      * status 404

      * path '_admin', 'reset'
      * method put
      * status 201

      * path 'schimpfos-wortos.php'
      * method get
      * status 200

  Scenario: Test mach catch all rule
    * url mockBaseUrl
    * path "does", "not", "exist"
    * method get
    * status 200
    * match response.status == "OK"


