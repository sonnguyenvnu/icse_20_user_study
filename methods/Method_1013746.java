public <T>T extract(String key,Function<String,T> function){
  genKeyValues();
  return function.apply(keyValues.get(key));
}
