/** 
 * Parse a key into parts
 * @param raw the key
 * @return parts
 */
public static List<ConfigurationKeyPart> parse(String raw,Map<String,String> contextOverrides){
  List<ConfigurationKeyPart> parts=Lists.newArrayList();
  int caret=0;
  for (; ; ) {
    int startIndex=raw.indexOf("${",caret);
    if (startIndex < 0) {
      break;
    }
    int endIndex=raw.indexOf("}",startIndex);
    if (endIndex < 0) {
      break;
    }
    if (startIndex > caret) {
      parts.add(new ConfigurationKeyPart(raw.substring(caret,startIndex),false));
    }
    startIndex+=2;
    if (startIndex < endIndex) {
      String name=raw.substring(startIndex,endIndex);
      if (contextOverrides != null && contextOverrides.containsKey(name)) {
        parts.add(new ConfigurationKeyPart(contextOverrides.get(name),false));
      }
 else {
        parts.add(new ConfigurationKeyPart(name,true));
      }
    }
    caret=endIndex + 1;
  }
  if (caret < raw.length()) {
    parts.add(new ConfigurationKeyPart(raw.substring(caret),false));
  }
  if (parts.size() == 0) {
    parts.add(new ConfigurationKeyPart("",false));
  }
  return parts;
}
