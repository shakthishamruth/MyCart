/*
  Make sure your Firebase project's '.read' and '.write' rules are set to 'true'. 
  Ignoring this will prevent the MCU from communicating with the database. 
  For more details- https://github.com/Rupakpoddar/ESP8266Firebase 
*/

#include <ESP8266Firebase.h>
#include <ESP8266WiFi.h>
#include <SPI.h>
#include <MFRC522.h>
// #include <map> // can use map
// using namespace std;

#define _SSID ""          // Your WiFi SSID
#define _PASSWORD ""      // Your WiFi Password
#define REFERENCE_URL ""  // Your Firebase project reference url

Firebase firebase(REFERENCE_URL);

constexpr uint8_t RST_PIN = D3;  // Configurable, see typical pin layout above
constexpr uint8_t SS_PIN = D4;   // Configurable, see typical pin layout above

MFRC522 rfid(SS_PIN, RST_PIN);  // Instance of the class
MFRC522::MIFARE_Key key;

String tag;

void setup() {
  Serial.begin(115200);
  SPI.begin();      // Init SPI bus
  rfid.PCD_Init();  // Init MFRC522
  pinMode(LED_BUILTIN, OUTPUT);
  digitalWrite(LED_BUILTIN, LOW);
  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  delay(1000);

  // Connect to WiFi
  Serial.println();
  Serial.println();
  Serial.print("Connecting to: ");
  Serial.println(_SSID);
  WiFi.begin(_SSID, _PASSWORD);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("-");
  }

  Serial.println("");
  Serial.println("WiFi Connected");

  // Print the IP address
  Serial.print("IP Address: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
  digitalWrite(LED_BUILTIN, HIGH);
}

void loop() {
  if (!rfid.PICC_IsNewCardPresent())
    return;
  if (rfid.PICC_ReadCardSerial()) {
    for (byte i = 0; i < 4; i++) {
      tag += rfid.uid.uidByte[i];
    }
    Serial.println(tag);
    // Can use map
    if (tag == "21713321620") {
      firebase.setString("basket3/item1/title", "Kerchief");
      firebase.setString("basket3/item1/desc", "5N");
      firebase.setInt("basket3/item1/price", 150);
      Serial.print("Upload Complete");
    } else if (tag == "511291751") {
      firebase.setString("basket3/item2/title", "Socks");
      firebase.setString("basket3/item2/desc", "1 Pair");
      firebase.setInt("basket3/item2/price", 100);
      Serial.print("Upload Complete");
    } else {
      firebase.setString("basket3/item/title", tag);
      firebase.setString("basket3/item/desc", "1N");
      firebase.setInt("basket3/item/price", 100);
      Serial.print("Upload Complete");
    }
    tag = "";
    rfid.PICC_HaltA();
    rfid.PCD_StopCrypto1();
  }
}
