# Solance
Banking System
Approach -

The first thing to do was to design the different APIs to be used. In this project, there are 5 APIs being used namely getuser, adduser, createaccount, depositamount and tansferamount. All of these apis help us to manage different back account of the user. For the timebeing, I used internal database H2, based on the requirement. To keep track of different transactions, I have used an internal queue which is being available throught the backend service. This queue is responsible of storing different message types and delivering it to the frontend. 
For the frontend, I have kept it simple by polling for messages from the backend queue and populating it in a table. 

Further development -
If the time was longer, I would implemented the following features -
- Using relational databse like MySQL
- Using Amazon SQS instead of internal sqs
- Implemented web sockets so that we don't have to poll everytime
- Improved UI by using MUI
- Handled transaction so that concurrent users cannot edit the same account
