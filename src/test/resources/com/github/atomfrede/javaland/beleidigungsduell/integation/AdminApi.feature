Feature: Test the beleidungs application admin API

  Background:
    * def appUrl = baseUrls.bleidigungs_app
    * url appUrl

    # Karate Schema Fuzzy Matching: https://github.com/karatelabs/karate#fuzzy-matching
    * def beleidigungSchema =
    """
    {
      id: '#number',
      beleidigungs_id: "#uuid",
      antwort_id: "#uuid",
      beleidigungs_template: "#string",
      antwort_template: "#string"
    }
    """

  Scenario: Test get all Beleidigungen and verify default parameters
    * path 'beleidigungen'
    * method get
    * status 200

    # Match schema in one line
    * match response == { _total_count: "#number", _params: { limit: 10, offset: 0}, items: "#[10] beleidigungSchema" }

    # Different ways to match schema of every item in items array
    * match response contains {items : "#[] beleidigungSchema"}
    * match response.items == "#[] beleidigungSchema"
    * match each response.items == beleidigungSchema

  Scenario: Test get all Beleidigungen and verify custom parameters are used
    * path 'beleidigungen'
    * param limit = 21
    * param offset = 4
    * method get
    * status 200
    * match response._params == { limit: 21, offset: 4}


  Scenario: Test creating a new Beleidigung, updating it, and reading the newly created Beleidigung
    * path 'beleidigungen'
    * def newBeleidigung =
    """
{
  beleidigungs_template: "Warum schämen sich %s für ihren Körper und nicht für ihren Kopf!",
  antwort_template: "Weil %s einfach Pech beim denken haben."
}
    """
    * request newBeleidigung
    * method post
    * status 200
    * def newCreatedBeleidigung = response
    * match newCreatedBeleidigung contains newBeleidigung
    * match newCreatedBeleidigung == {id: '#number', beleidigungs_id: "#uuid", antwort_id: "#uuid", beleidigungs_template: "#string", antwort_template: "#string"}

    * path 'beleidigungen', response.id
    * set newCreatedBeleidigung.antwort_template = "%s und Pech beim denken!"
    * request newCreatedBeleidigung
    * method put
    * status 204

    * path 'beleidigungen', newCreatedBeleidigung.id
    * method get
    * status 200
    * match response == newCreatedBeleidigung
