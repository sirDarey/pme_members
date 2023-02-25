# PME MEMBERS API TASK
*A simple API to manage PME Members.*

###### Tech Stack Used: 
        Java, SpringBoot, MySQL DB

***Author: Sir Darey***

### List of End Points

        POST `/api/members`

        GET `/api/members`

        GET `/api/members/{id}`

        PUT  `/api/members/{id}`

        DELETE `/api/members/{id}`


###  Sample Json `REQUEST` For `POST`
    
       {
            "firstName" : "Sir",
            "lastName" : "Darey",
            "email" : "darey1oguns@gmail.com",
            "countryCode" : 234,
            "phoneNo" : 7010854698,
            "address" : "address address address address  address",
            "track" : "Backend",
            "stack" : ["Java" , "SpringBoot"],
            "password" : "darey1Oguns@",
            "confirmPassword" : "darey1Oguns@"
        } 


###  Sample Json `RESPONSE` For `POST`

        {
            "message": "New Member Created Successfully",
            "data": {
                "memberId": 1,
                "firstName": "Sir",
                "lastName": "Darey",
                "email": "darey1oguns@gmail.com",
                "phoneNo": "+2347010854698",
                "address": "address address address address  address",
                "track": "Backend",
                "stack": [
                    {
                        "stackId": 5,
                        "name": "Java"
                    },
                    {
                        "stackId": 6,
                        "name": "SpringBoot"
                    }
                ],
                "memberEnabled": false,
                "lastLogin": null,
                "dateRegistered": "25-02-2023 14:15:26",
                "lastFailedLogin": null
            }
        }


###  Sample Json `RESPONSE` For `GET`

        {
            "message": "Retrieved Member's Details Successfully",
            "data": {
                "memberId": 1,
                "firstName": "Sir",
                "lastName": "Darey",
                "email": "darey1oguns@gmail.com",
                "phoneNo": "+2347010854698",
                "address": "address address address address  address",
                "track": "Backend",
                "stack": [
                    {
                        "stackId": 5,
                        "name": "Java"
                    },
                    {
                        "stackId": 6,
                        "name": "SpringBoot"
                    }
                ],
                "memberEnabled": false,
                "lastLogin": null,
                "dateRegistered": "25-02-2023 14:15:26",
                "lastFailedLogin": null
            }
        }


###  Sample Json `REQUEST` For `PUT`         
    
    {
            "firstName" : "Sir",
            "lastName" : "Darey",
            "email" : "darey1oguns@gmail.com",
            "countryCode" : 234,
            "phoneNo" : 7010854698,
            "address" : "address address address address  address",
            "track" : "Backend",
            "stack" : ["Java" , "SpringBoot"]
    }


###  Sample Json `RESPONSE` For `PUT`

        {
            "message": "Member's Details UPDATED Successfully",
            "data": {
                "memberId": 1,
                "firstName": "Sir",
                "lastName": "Darey",
                "email": "darey1oguns@gmail.com",
                "phoneNo": "+2347010854698",
                "address": "address address address address  address",
                "track": "Backend",
                "stack": [
                    {
                        "stackId": 5,
                        "name": "Java"
                    },
                    {
                        "stackId": 6,
                        "name": "SpringBoot"
                    }
                ],
                "memberEnabled": false,
                "lastLogin": null,
                "dateRegistered": "25-02-2023 14:15:26",
                "lastFailedLogin": null
            }
        }


###  Available List of `stack` for any Json request

        1. JavaScript
        2. React
        3. Python
        4. Django
        5. Java
        6. SpringBoot
        7. C#
        8. .NET Core
        9. PHP
        10. Flutter