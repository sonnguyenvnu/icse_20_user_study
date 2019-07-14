@Override protected void loadExchangeMetaData(InputStream is){
  wexMetaData=loadMetaData(is,WexMetaData.class);
  exchangeMetaData=WexAdapters.toMetaData(null,wexMetaData);
}
