**Get all meals**
curl --location 'http://localhost:8080/topjava/rest/profile/meals'

**Get meal by id**
curl --location 'http://localhost:8080/topjava/rest/profile/meals/100009'

**Delete meal by id**
curl --location --request DELETE 'http://localhost:8080/topjava/rest/profile/meals/100009'

**Create new meal**
curl --location 'http://localhost:8080/topjava/rest/profile/meals' \
--header 'Content-Type: application/json' \
--data '`
{
"id":null,
"dateTime": "2024-03-27T01:00",
"description": "Писча",
"calories": 1000
}`'

**Update meal by id**
curl --location --request PUT 'http://localhost:8080/topjava/rest/profile/meals/100013' \
--header 'Content-Type: application/json' \
--data '
`{
"id":null,
"dateTime": "2024-03-27T01:00",
"description": "Не Писча",
"calories": 999
}`'

**Filter meals by date and time**
curl --location 'http://localhost:8080/topjava/rest/profile/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime=09%3A00&endTime=14%3A00'

**Filter meals without values**
curl --location 'http://localhost:8080/topjava/rest/profile/meals/filter?startDate=&endTime='