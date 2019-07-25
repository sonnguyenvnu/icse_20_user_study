@Override public void put(String key,String value){
  request.addHeader(new BasicHeader(key,value));
}
