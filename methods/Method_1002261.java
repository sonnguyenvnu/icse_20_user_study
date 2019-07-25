private void setup(String propName,Set<T> defaultValue,String delimiterRegex){
  setup(propName,defaultValue,Splitter.onPattern(delimiterRegex).omitEmptyStrings().trimResults());
}
