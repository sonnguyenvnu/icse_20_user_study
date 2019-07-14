public EnumFromStringHelper<T> addJsonStringMapping(String jsonString,final T enumVal){
  fromString.put(jsonString,enumVal);
  return this;
}
