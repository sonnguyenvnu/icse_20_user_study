/** 
 * ??????????
 * @param responseBaggage ??????
 */
public void putAllResponseBaggage(Map<String,String> responseBaggage){
  if (BAGGAGE_ENABLE && responseBaggage != null) {
    this.responseBaggage.putAll(responseBaggage);
  }
}
