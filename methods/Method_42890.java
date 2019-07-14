@Override protected void loadExchangeMetaData(InputStream is){
  anxMetaData=loadMetaData(is,ANXMetaData.class);
  exchangeMetaData=anxMetaData;
}
