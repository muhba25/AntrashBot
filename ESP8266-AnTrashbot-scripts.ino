#include <Servo.h>
#define servoPin 16
//#include <WiFiManager.h>
#include  <AntaresESP8266HTTP.h >

#define ACCESSKEY "5412378e242f5814:29a48f99807600d4"    
#define projectName "Adrian_IOT"
#define deviceName "TrashBot" 
#define WIFISSID "Eproc"         // Ganti dengan SSID WiFi anda
#define PASSWORD "cintamani"     // Ganti dengan password WiFi anda
AntaresESP8266HTTP antares(ACCESSKEY);
bool opentrash;


Servo servo;
int const trigPin = 14;
int const echoPin = 12;

void setup() {
  Serial.begin(115200);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  servo.attach(servoPin);
  antares.setDebug(true);
  antares.wifiConnection(WIFISSID,PASSWORD);

}

void loop() {
  int duration, distance;
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);

  duration = pulseIn(echoPin, HIGH);
  distance = (duration / 2) / 29.1;
  antares.get(projectName, deviceName);
  int opentrash = antares.getInt("status");

  
  if(opentrash==1){
    servo.write(180);
    delay(200);
  }else{
      if (distance <= 60 && distance > 0) {
        servo.write(180);
        delay(500);
    
      } else {
        servo.write(-180);
        antares.add("status", 0);
        antares.send(projectName, deviceName);
      }
  }

   
  Serial.print(distance);
  Serial.println(" cm");
  Serial.print("Status: ");
  Serial.println(opentrash);

  delay(60);

}
