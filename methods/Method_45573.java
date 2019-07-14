/** 
 * ????????
 * @param key   Key
 * @param value Value
 */
public void putRequestBaggage(String key,String value){
  if (BAGGAGE_ENABLE && key != null && value != null) {
    requestBaggage.put(key,value);
  }
}
