/** 
 * ??????????
 * @param requestBaggage ??????
 */
public void putAllRequestBaggage(Map<String,String> requestBaggage){
  if (BAGGAGE_ENABLE && requestBaggage != null) {
    this.requestBaggage.putAll(requestBaggage);
  }
}
