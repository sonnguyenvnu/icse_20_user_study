@Override public String mapKey2Field(String key,KeyInformation keyInfo){
  IndexProvider.checkKeyValidity(key);
  key=key.replace(' ',REPLACEMENT_CHAR);
  if (!dynFields)   return key;
  if (ParameterType.MAPPED_NAME.hasParameter(keyInfo.getParameters()))   return key;
  String postfix;
  final Class dataType=keyInfo.getDataType();
  if (AttributeUtil.isString(dataType)) {
    final Mapping map=getStringMapping(keyInfo);
switch (map) {
case TEXT:
      postfix="_t";
    break;
case STRING:
  postfix="_s";
break;
default :
throw new IllegalArgumentException("Unsupported string mapping: " + map);
}
}
 else if (AttributeUtil.isWholeNumber(dataType)) {
if (dataType.equals(Long.class)) postfix="_l";
 else postfix="_i";
}
 else if (AttributeUtil.isDecimal(dataType)) {
if (dataType.equals(Float.class)) postfix="_f";
 else postfix="_d";
}
 else if (dataType.equals(Geoshape.class)) {
postfix="_g";
}
 else if (dataType.equals(Date.class) || dataType.equals(Instant.class)) {
postfix="_dt";
}
 else if (dataType.equals(Boolean.class)) {
postfix="_b";
}
 else if (dataType.equals(UUID.class)) {
postfix="_uuid";
}
 else throw new IllegalArgumentException("Unsupported data type [" + dataType + "] for field: " + key);
if (keyInfo.getCardinality() == Cardinality.SET || keyInfo.getCardinality() == Cardinality.LIST) {
postfix+="s";
}
return key + postfix;
}
