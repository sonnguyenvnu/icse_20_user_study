/** 
 * Checks, if there is an even number of unescaped #
 */
@Override public List<IntegrityMessage> check(BibEntry entry){
  List<IntegrityMessage> results=new ArrayList<>();
  Map<String,String> fields=entry.getFieldMap();
  for (  Map.Entry<String,String> field : fields.entrySet()) {
    if (!InternalBibtexFields.getFieldProperties(field.getKey()).contains(FieldProperty.VERBATIM)) {
      Matcher hashMatcher=UNESCAPED_HASH.matcher(field.getValue());
      int hashCount=0;
      while (hashMatcher.find()) {
        hashCount++;
      }
      if ((hashCount & 1) == 1) {
        results.add(new IntegrityMessage(Localization.lang("odd number of unescaped '#'"),entry,field.getKey()));
      }
    }
  }
  return results;
}
