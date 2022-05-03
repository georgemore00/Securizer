# Overview
**Securizer** is an Multi-factor Authentication Server which uses standard Username & Password Authentication and OTP(One-time password).
The Authentication Server makes use of Twillio(SMS messaging service) to send the One-time passwords in SMS to the user's cellphone.

## Technologies

- Spring Boot
- Spring WEB 
- Spring Data JPA (H2 Embedded in-memory database)
- Spring Security for stateless JWT authentication & authorization

## Requirements
To use the project you must first head over to www.twilio.com/try-twilio and register a free account.
Each free account comes with an initial 14$ and a free phone number which can be used to recieve and send SMS/phone calls.

Lastly you will need to configure the project's application.properties with your personal Twillio account information.
``` shell
TWILIO_ACCOUNT_SID =
TWILIO_AUTH_TOKEN =
TWILLIO_PHONE_NUMBER =
USER_PHONE_NUMBER =
```

## Authentication flow
![Securizer flow](Securizer%20flow%20chart.drawio.png)
