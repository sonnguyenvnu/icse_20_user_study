@Override public String toString(T target,long features,Set<String> ignoreProperty){
  if (target == null) {
    return "";
  }
  if (features == -1) {
    features=defaultFeatures;
  }
  Map<String,Object> mapValue=toMap(target,features,ignoreProperty);
  if (ToString.Feature.hasFeature(features,ToString.Feature.jsonFormat)) {
    return JSON.toJSONString(mapValue);
  }
  boolean writeClassName=ToString.Feature.hasFeature(features,ToString.Feature.writeClassname);
  StringJoiner joiner=new StringJoiner(", ",(writeClassName ? target.getClass().getSimpleName() : "") + "{","}");
  mapValue.forEach((key,value) -> joiner.add(key.concat("=").concat(String.valueOf(value))));
  return joiner.toString();
}
