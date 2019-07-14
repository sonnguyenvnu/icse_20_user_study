@Override public void applySpecification(ExchangeSpecification exchangeSpecification){
  super.applySpecification(exchangeSpecification);
  if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Intl").equals(false) && exchangeSpecification.getExchangeSpecificParametersItem("Use_Futures").equals(true)) {
    throw new RuntimeException("Futures only available on international version. Set `Use_Intl` to true.");
  }
  concludeHostParams(exchangeSpecification);
}
