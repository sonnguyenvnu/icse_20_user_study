/** 
 * @param value true or false
 */
public static void digitalWrite(int pin,boolean value){
  if (value) {
    digitalWrite(pin,HIGH);
  }
 else {
    digitalWrite(pin,LOW);
  }
}
