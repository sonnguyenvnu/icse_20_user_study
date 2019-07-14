@Override public void applySpecification(ExchangeSpecification exchangeSpecification){
  ExchangeSpecification defaultSpecification=getDefaultExchangeSpecification();
  if (exchangeSpecification == null) {
    this.exchangeSpecification=defaultSpecification;
  }
 else {
    if (exchangeSpecification.getExchangeName() == null) {
      exchangeSpecification.setExchangeName(defaultSpecification.getExchangeName());
    }
    if (exchangeSpecification.getExchangeDescription() == null) {
      exchangeSpecification.setExchangeDescription(defaultSpecification.getExchangeDescription());
    }
    if (exchangeSpecification.getSslUri() == null) {
      exchangeSpecification.setSslUri(defaultSpecification.getSslUri());
    }
    if (exchangeSpecification.getHost() == null) {
      exchangeSpecification.setHost(defaultSpecification.getHost());
    }
    if (exchangeSpecification.getPlainTextUri() == null) {
      exchangeSpecification.setPlainTextUri(defaultSpecification.getPlainTextUri());
    }
    if (exchangeSpecification.getExchangeSpecificParameters() == null) {
      exchangeSpecification.setExchangeSpecificParameters(defaultSpecification.getExchangeSpecificParameters());
    }
 else {
      for (      Map.Entry<String,Object> entry : defaultSpecification.getExchangeSpecificParameters().entrySet()) {
        if (exchangeSpecification.getExchangeSpecificParametersItem(entry.getKey()) == null) {
          exchangeSpecification.setExchangeSpecificParametersItem(entry.getKey(),entry.getValue());
        }
      }
    }
    this.exchangeSpecification=exchangeSpecification;
  }
  if (this.exchangeSpecification.getMetaDataJsonFileOverride() != null) {
    InputStream is=null;
    try {
      is=new FileInputStream(this.exchangeSpecification.getMetaDataJsonFileOverride());
      loadExchangeMetaData(is);
    }
 catch (    FileNotFoundException e) {
      logger.warn("An exception occured while loading the metadata file from the classpath. This is just a warning and can be ignored, but it may lead to unexpected results, so it's better to address it.",e);
    }
 finally {
      IOUtils.closeQuietly(is);
    }
  }
 else   if (this.exchangeSpecification.getExchangeName() != null) {
    InputStream is=null;
    try {
      is=BaseExchangeService.class.getClassLoader().getResourceAsStream(getMetaDataFileName(exchangeSpecification) + ".json");
      loadExchangeMetaData(is);
    }
  finally {
      IOUtils.closeQuietly(is);
    }
  }
 else {
    logger.warn("No \"exchange name\" found in the ExchangeSpecification. The name is used to load the meta data file from the classpath and may lead to unexpected results.");
  }
  initServices();
  if (this.exchangeSpecification.isShouldLoadRemoteMetaData()) {
    try {
      logger.info("Calling Remote Init...");
      remoteInit();
    }
 catch (    IOException e) {
      throw new ExchangeException(e);
    }
  }
}
