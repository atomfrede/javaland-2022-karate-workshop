Feature:

  Background:
    * def appUrl = baseUrls.bleidigungs_app
    * url appUrl
    

  Scenario: Test 
    * driver appUrl
    * delay(2000)
    * submit().click('#submit')
    * delay(2000)
    * match exists('#won-image') == true


