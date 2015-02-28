# CurrencyProcessor

Prerequisites JAVA 1.7, Maven

This application exposes an endpoint which consumes messages in JSON format. This application is not connected to any database in this implementation but the 
posted messages are stored in the appliction context. The messages are alive in the context till the web application is up.

Using highcharts have derived three graphs which shows trends and groups messages on Currency From, Currency To and Originating countries of the message.

On the Application Front End, three tabs Dashboard, Messages and Post message to display graphs, list of messages received and to post a message respectively.

Unit test cases have written to cover the code written.

The application front end is developed using Bootstrap 3.3.2

