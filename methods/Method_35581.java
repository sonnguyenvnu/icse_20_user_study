public ResponseDefinitionBuilder withHeader(String key,String... values){
  headers.add(new HttpHeader(key,values));
  return this;
}
