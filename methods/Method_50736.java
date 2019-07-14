private void addVariableToMapping(final String variableName,final String type){
switch (type.toLowerCase(Locale.ROOT)) {
case "list":
case "map":
    break;
default :
  varToTypeMapping.put(variableName,getSimpleType(type));
break;
}
}
