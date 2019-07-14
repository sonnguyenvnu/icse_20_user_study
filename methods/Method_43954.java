/** 
 * Create  {@link TradeHistoryParams} object specific to this exchange. Object created by thismethod may be used to discover supported and required  {@link #getTradeHistory(TradeHistoryParams)} parameters and should be passed only to the method in thesame class as the createTradeHistoryParams that created the object.
 */
default TradeHistoryParams createTradeHistoryParams(){
  throw new NotYetImplementedForExchangeException();
}
