Feature: Beleidigungs App UI Test

  Background:
    * def appUrl = baseUrls.bleidigungs_app
    * url appUrl
    

  Scenario: Test selecting the right answer
    * url appUrl
    * path 'beleidigungen'
    * param limit = 1
    * param offset = 0
    * method get
    * status 200
    * match response contains {items: '#[1]' }
    * def beleidigung = response.items[0]

    * print beleidigung
    * def antwortIdSelector = '#I' + beleidigung.antwort_id
    * driver appUrl + '?fixedId=' + beleidigung.beleidigungs_id
    * delay(2000)

    * match exists(antwortIdSelector) == true
    * click(antwortIdSelector)
    * submit().click('#submit')
    * delay(2000)
    * match exists('#won-image') == true


