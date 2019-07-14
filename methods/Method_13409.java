private static void addParam(MultiValueMap<String,String> paramsMap,String name,String value){
  String paramValue=trimAllWhitespace(value);
  if (!StringUtils.hasText(paramValue)) {
    paramValue=EMPTY_VALUE;
  }
  paramsMap.add(trimAllWhitespace(name),paramValue);
}
