public String getKey(String key){
  String q=getQuote();
  return (isKeyPrefix() ? getAlias() + "." : "") + q + key + q;
}
