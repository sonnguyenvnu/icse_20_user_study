/** 
 * Fanout?? ???Exchange
 */
@Bean public FanoutExchange fanoutExchage(){
  return new FanoutExchange(FANOUT_EXCHANGE);
}
