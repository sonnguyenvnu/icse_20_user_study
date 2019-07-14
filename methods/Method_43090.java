/** 
 * Each element in the response array contains a set of currencies that are at a given fee tier. The API returns the fee per currency in each tier and does not make any promises that they are all the same, so this adapter will use the fee per currency instead of the fee per tier.
 */
public static Map<CurrencyPair,Fee> adaptDynamicTradingFees(BitfinexTradingFeeResponse[] responses,List<CurrencyPair> currencyPairs){
  Map<CurrencyPair,Fee> result=new HashMap<>();
  for (  BitfinexTradingFeeResponse response : responses) {
    BitfinexTradingFeeResponse.BitfinexTradingFeeResponseRow[] responseRows=response.getTradingFees();
    for (    BitfinexTradingFeeResponse.BitfinexTradingFeeResponseRow responseRow : responseRows) {
      Currency currency=Currency.getInstance(responseRow.getCurrency());
      BigDecimal percentToFraction=BigDecimal.ONE.divide(BigDecimal.ONE.scaleByPowerOfTen(2));
      Fee fee=new Fee(responseRow.getMakerFee().multiply(percentToFraction),responseRow.getTakerFee().multiply(percentToFraction));
      for (      CurrencyPair pair : currencyPairs) {
        if (pair.base.equals(currency)) {
          if (result.put(pair,fee) != null) {
            throw new IllegalStateException("Fee for currency pair " + pair + " is overspecified");
          }
        }
      }
    }
  }
  return result;
}
