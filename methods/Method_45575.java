/** 
 * ????????
 * @param key   Key
 * @param value Value
 */
public void putResponseBaggage(String key,String value){
  if (BAGGAGE_ENABLE && key != null && value != null) {
    responseBaggage.put(key,value);
  }
}
