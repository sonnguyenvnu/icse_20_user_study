private String convertValue(Object value){
  return convertMap.getOrDefault(value.getClass(),defaultConvert).apply(value);
}
