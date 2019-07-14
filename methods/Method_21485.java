private String findFieldValue(String header,Map<String,Object> doc,boolean flat,String separator){
  if (flat && header.contains(".")) {
    String[] split=header.split("\\.");
    Object innerDoc=doc;
    for (    String innerField : split) {
      if (!(innerDoc instanceof Map)) {
        return separator;
      }
      innerDoc=((Map<String,Object>)innerDoc).get(innerField);
      if (innerDoc == null) {
        return separator;
      }
    }
    return innerDoc.toString() + separator;
  }
 else {
    if (doc.containsKey(header)) {
      return String.valueOf(doc.get(header)) + separator;
    }
  }
  return separator;
}
