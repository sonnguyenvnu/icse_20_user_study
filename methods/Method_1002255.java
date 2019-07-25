private void setup(String propName,List<T> defaultValue,String delimiterRegex){
  setup(propName,defaultValue,Splitter.onPattern(delimiterRegex).omitEmptyStrings().trimResults());
}
