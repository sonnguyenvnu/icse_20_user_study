private static StringBuilder replace(String wmlTemplateString,StringBuilder strB,java.util.Map<String,?> mappings){
  int offset=0;
  while (true) {
    int startKey=wmlTemplateString.indexOf("${",offset);
    if (startKey == -1)     return strB.append(wmlTemplateString.substring(offset));
 else {
      strB.append(wmlTemplateString.substring(offset,startKey));
      int keyEnd=wmlTemplateString.indexOf('}',startKey);
      String key=wmlTemplateString.substring(startKey + 2,keyEnd);
      Object val=mappings.get(key);
      if (val == null) {
        log.warn("Invalid key '" + key + "' or key not mapped to a value");
        strB.append(key);
      }
 else {
        strB.append(val.toString());
      }
      offset=keyEnd + 1;
    }
  }
}
