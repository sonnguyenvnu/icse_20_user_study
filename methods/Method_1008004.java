/** 
 * Matches and returns any named captures within a compiled grok expression that matched within the provided text.
 * @param text the text to match and extract values from.
 * @return a map containing field names and their respective coerced values that matched.
 */
public Map<String,Object> captures(String text){
  byte[] textAsBytes=text.getBytes(StandardCharsets.UTF_8);
  Map<String,Object> fields=new HashMap<>();
  Matcher matcher=compiledExpression.matcher(textAsBytes);
  int result=matcher.search(0,textAsBytes.length,Option.DEFAULT);
  if (result != -1 && compiledExpression.numberOfNames() > 0) {
    Region region=matcher.getEagerRegion();
    for (Iterator<NameEntry> entry=compiledExpression.namedBackrefIterator(); entry.hasNext(); ) {
      NameEntry e=entry.next();
      String groupName=new String(e.name,e.nameP,e.nameEnd - e.nameP,StandardCharsets.UTF_8);
      for (      int number : e.getBackRefs()) {
        if (region.beg[number] >= 0) {
          String matchValue=new String(textAsBytes,region.beg[number],region.end[number] - region.beg[number],StandardCharsets.UTF_8);
          GrokMatchGroup match=new GrokMatchGroup(groupName,matchValue);
          fields.put(match.getName(),match.getValue());
          break;
        }
      }
    }
    return fields;
  }
 else   if (result != -1) {
    return fields;
  }
  return null;
}
