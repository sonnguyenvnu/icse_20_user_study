private Object findFieldValue(String header,Map<String,Object> doc,boolean flat){
  if (flat && header.contains(".")) {
    String[] split=header.split("\\.");
    Object innerDoc=doc;
    for (    String innerField : split) {
      if (!(innerDoc instanceof Map)) {
        return null;
      }
      innerDoc=((Map<String,Object>)innerDoc).get(innerField);
      if (innerDoc == null) {
        return null;
      }
    }
    return innerDoc;
  }
 else {
    if (doc.containsKey(header)) {
      return doc.get(header);
    }
  }
  return null;
}
