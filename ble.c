
#include <SoftwareSerial.h>
SoftwareSerial mySerial(2, 3); // RX, TX



void setup() {
  // put your setup code here, to run once:
  mySerial.begin(115200);
  Serial.begin(115200);

 // sendCommand("AT");
 // sendCommand("AT+ROLE0");
 // sendCommand("AT+UUID0xFFE0");
 // sendCommand("AT+CHAR0xFFE1");
 // sendCommand("AT+NAMEbluino");
 //Indexes are: 1:1200, 2:2400, 3:4800, 4:9600, 5:19200, 6:38400, 7:57600, 8:115200, 9:230400, A:460800, B:921600, C:1382400

//Send: AT+BAUD<index>
  sendCommand("AT+BAUD8");
  
}

void sendCommand(const char * command) {
  Serial.print("Command send :");
  Serial.println(command);
  mySerial.println(command);
  //wait some time
  delay(100);

  char reply[100];
  int i = 0;
  while (mySerial.available()) {
    reply[i] = mySerial.read();
    i += 1;
  }
  //end the string
  reply[i] = '\0';
  Serial.print(reply);
  Serial.println("Reply end");
  delay(50);
}

void readSerial(){
  char reply[50];
  int i = 0;
  while (mySerial.available()) {
    reply[i] = mySerial.read();
    i += 1;
  }
  //end the string
  reply[i] = '\0';
  if(strlen(reply) > 0){
    Serial.print(reply);
    Serial.println(" We have just read some data");
  }
}
void writeSerialToBLE(int value) {
  mySerial.println(value);
}

void writeToBLE(char value) {
  Serial.print("Writing hex :");
  Serial.println(value, HEX);
  mySerial.write(value);
}

char j = 0;
void loop() {
  //writeToBLE(j);
  //j += 1;
    readSerial();

 delay(500);
}
