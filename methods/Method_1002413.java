@Override public void put(String listenTo,String discoveryProperties) throws PropertyStoreException {
  try {
    _store.put(listenTo,_store.getSerializer().fromBytes(discoveryProperties.getBytes("UTF-8")));
  }
 catch (  PropertySerializationException e) {
    throw new PropertyStoreException(e);
  }
catch (  UnsupportedEncodingException e) {
    throw new RuntimeException("UTF-8 should never fail",e);
  }
}
