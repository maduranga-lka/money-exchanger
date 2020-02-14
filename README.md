# money-exchanger

-**Get All Currency Rates** : (GET): /moneyexchanger/exchanges/rates  
-**Get Rates for Particular** : Currnecy(GET): /moneyexchanger/exchanges/rates/USD  
-**Check Selling Amount(GET)** : /moneyexchanger/exchanges/sell/HKD/200  
-**Check Buying Amount(GET)** : /moneyexchanger/exchanges/buy/USD/1  
-**Add Exchange Transaction(POST)** : /moneyexchanger/exchanges/transactions/USD/100  

**Request Sample :**   
{  
 "currency":"USD",  
 "buyRate":"1.1",  
 "sellRate":"1.1",  
 "amount":"1000"  
}      

**Get All Exchange Transactions** : /moneyexchanger/exchanges/transactions       

**Actuator**    
/moneyexchanger/actuator    

**Swagger UI**    
/moneyexchanger/swagger-ui.html    

**Swagger API Docs**    
/moneyexchanger/v2/api-docs    

**Application Logs**    
/moneyexchanger/logs    

**Local Log Path**    
\money-exchanger\logs\exchanger-logger.log    
