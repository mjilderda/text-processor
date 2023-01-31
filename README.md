# Text-processor applicatie

### Beschrijving
Text-processor applicatie voor het doen van analyses op een tekst.<br />
Het betreft een Spring-boot applicatie en heeft de volgende 3 endpoints:

#### /api/v1/textprocessor/frequency
_parameters:_
* input: String word & String text
* output:
    * status 200: int frequency
    * status 400: string foutmelding

Levert het aantal keer dat het ingevoerde woord in de tekst voorkomt.

#### /api/v1/textprocessor/highest
_parameters:_
* input: String text
* output: 
  * status 200: int frequency
  * status 400: string foutmelding

Levert het aantal voorkomens van het meestvoorkomende woord in de tekst.

#### /api/v1/textprocessor/mostFrequentNwords
_parameters:_
* input: int numberOfWords & String text
* output: 
  * status 200: list van frequency_dto's 
  * status 400: string foutmelding

Levert een lijst met de meestvoorkomende woorden en bijbehorende frequentie. De value numberOfWords geeft het aantal woorden dat maximaal teruggegeven mag worden. <br />
Indien de tekst minder woorden dan deze waarde bevat, worden alle woorden geretourneerd.

#### OpenApi-schema
Een openApi-schema is gebruikt om de endpoints, DTO's en documentatie te genereren.  

#### DTO'S
Communicatie met de api's gaat middels DTO'S. Deze staan beschreven in de swagger documentatie.

### project bouwen en lokaal draaien
Het bouwen van het project gaat middels het `mvn install` command of via het maven menu in intelliJ.

In de starter package is de Spring-boot applicatie middels de TextProcessorStarter class op te starten.
Het lokale base-path is http://localhost:8080

### Documentatie
Swagger documentatie is, bij het lokaal draaien van de applicatie, te vinden via: http://localhost:8080/swagger-ui.html

### Testen
De controller, mapper, service en utils zijn voorzien  van unittesten. Deze worden bij de maven-build gedraaid, maar kunnen ook handmatig in intellij gedraaid worden.
