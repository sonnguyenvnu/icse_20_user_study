private static String query(MultiValueMap<String,String> params){
  StringBuilder builder=new StringBuilder();
  Iterator<Map.Entry<String,List<String>>> iterator=params.entrySet().iterator();
  if (iterator.hasNext()) {
    Map.Entry<String,List<String>> param=iterator.next();
    String key=param.getKey();
    List<String> valueList=param.getValue();
    if (!ObjectUtils.isEmpty(valueList)) {
      for (      String value : valueList) {
        builder.append(key).append("=").append(value);
      }
    }
 else {
      builder.append(key).append("=");
    }
  }
  while (iterator.hasNext()) {
    Map.Entry<String,List<String>> param=iterator.next();
    String key=param.getKey();
    List<String> valueList=param.getValue();
    if (!ObjectUtils.isEmpty(valueList)) {
      for (      String value : valueList) {
        builder.append("&").append(key).append("=").append(value);
      }
    }
 else {
      builder.append("&").append(key).append("=");
    }
  }
  return builder.toString();
}
