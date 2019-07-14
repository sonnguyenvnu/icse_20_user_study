public static void verifyCurrencyPairAvailability(CurrencyPair currencyPair){
  if (!availablePairs.contains(currencyPair)) {
    throw new NotAvailableFromExchangeException();
  }
}
