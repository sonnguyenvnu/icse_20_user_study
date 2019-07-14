/** 
 * After period of time, the deltaServerTime may not accurate again. Need to catch the "Timestamp for this request was 1000ms ahead" exception and refresh the deltaServerTime.
 */
public void refreshTimestamp(){
  ((BinanceExchange)exchange).clearDeltaServerTime();
}
