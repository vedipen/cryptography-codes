# Man In The Middle

[Back](../../../)

### Run

##### Terminal Window 1 - 
```
javac Eve.java
java Eve
```
```
Public Key p is : 13
Public Key g is : 6
Private Key of Eve is : 7
Enter the port you want to connect to : 2017
Waiting for client on port 2017...
```
After Connecting to Alice
```
Just connected to /127.0.0.1:34990
R3 generated is 7
R1 recieved is 8
Generated key is 5
Cipher recieved is MJQQT
Message sent from Alice is hello
Waiting for client on port 2017...
```
After Connecting to Bob
```
Just connected to /127.0.0.1:34992
R3 generated is 7
R2 recieved is 4
Generated key is 4
Send message to Bob (hello) (Hit enter to send the same message):
Generated Cipher is : LIPPS
Sent Cipher to Bob successfully
```
##### Terminal Window 2 -
```
javac Alice.java
java Alice
```
```
Public Key p is : 13
Public Key g is : 6
Private Key of Alice is : 3
Enter port you want to connect to : 2017
Connecting to localhost on port 2017
Just connected to localhost/127.0.0.1:2017
R1 generated is 8
R3 recieved from Eve is 7
Sending R1 to Alice
Generated key is 5
Enter message to send : hello
Generated Cipher is : MJQQT
Sent Cipher to Eve suceessfully
```
##### Terminal Window 3 -
```
javac Bob.java
java Bob
```
```
Public Key p is : 13
Public Key g is : 6
Private Key of Bob is : 10
Enter port you want to connect to : 2017
Connecting to localhost on port 2017
Just connected to localhost/127.0.0.1:2017
R1 generated is 4
R3 recieved from Eve is 7
Sending R2 to Eve
Generated key is 4
Cipher recieved is LIPPS
Message sent from Eve is hello
```
