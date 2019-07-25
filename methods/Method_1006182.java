/** 
 * Detect any non ASCII encoded characters, e.g., umlauts or unicode in the fields
 */
@Override public List<IntegrityMessage> check(BibEntry entry){
  List<IntegrityMessage> results=new ArrayList<>();
  for (  Map.Entry<String,String> field : entry.getFieldMap().entrySet()) {
    boolean asciiOnly=CharMatcher.ascii().matchesAllOf(field.getValue());
    if (!asciiOnly) {
      results.add(new IntegrityMessage(Localization.lang("Non-ASCII encoded character found"),entry,field.getKey()));
    }
  }
  return results;
}
