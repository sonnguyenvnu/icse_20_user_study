/** 
 * Extract contract used by spec 
 */
public static FuturesContract futuresContractOfConfig(ExchangeSpecification exchangeSpecification){
  FuturesContract contract;
  if (exchangeSpecification.getExchangeSpecificParameters().containsKey("Futures_Contract")) {
    contract=(FuturesContract)exchangeSpecification.getExchangeSpecificParameters().get("Futures_Contract");
  }
 else   if (exchangeSpecification.getExchangeSpecificParameters().containsKey("Futures_Contract_String")) {
    contract=FuturesContract.valueOfIgnoreCase(FuturesContract.class,(String)exchangeSpecification.getExchangeSpecificParameters().get("Futures_Contract_String"));
  }
 else {
    throw new RuntimeException("`Futures_Contract` or `Futures_Contract_String` not defined in exchange specific parameters.");
  }
  return contract;
}
