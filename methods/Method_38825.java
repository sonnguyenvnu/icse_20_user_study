protected String generateAttributeValue(final Map<String,String> map,final char propertiesDelimiter,final char valueDelimiter){
  StringBuilder sb=new StringBuilder(map.size() * 32);
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    sb.append(entry.getKey());
    sb.append(valueDelimiter);
    sb.append(entry.getValue());
    sb.append(propertiesDelimiter);
  }
  return sb.toString();
}
