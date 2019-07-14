@Override public String getMetaDataFileName(ExchangeSpecification exchangeSpecification){
  if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Intl").equals(false)) {
    return exchangeSpecification.getExchangeName().toLowerCase().replace(" ","").replace("-","").replace(".","") + "_china";
  }
 else {
    if (exchangeSpecification.getExchangeSpecificParametersItem("Use_Futures").equals(true)) {
      return exchangeSpecification.getExchangeName().toLowerCase().replace(" ","").replace("-","").replace(".","") + "_futures";
    }
 else {
      return exchangeSpecification.getExchangeName().toLowerCase().replace(" ","").replace("-","").replace(".","") + "_intl";
    }
  }
}
