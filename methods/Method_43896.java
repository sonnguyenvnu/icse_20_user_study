public String getMetaDataFileName(ExchangeSpecification exchangeSpecification){
  return exchangeSpecification.getExchangeName().toLowerCase().replace(" ","").replace("-","").replace(".","");
}
