/** 
 * Checks, if there are any HTML encoded characters in nonverbatim fields.
 */
@Override public List<IntegrityMessage> check(BibEntry entry){
  List<IntegrityMessage> results=new ArrayList<>();
  for (  Map.Entry<String,String> field : entry.getFieldMap().entrySet()) {
    if (InternalBibtexFields.getFieldProperties(field.getKey()).contains(FieldProperty.VERBATIM)) {
      continue;
    }
    Matcher characterMatcher=HTML_CHARACTER_PATTERN.matcher(field.getValue());
    if (characterMatcher.find()) {
      results.add(new IntegrityMessage(Localization.lang("HTML encoded character found"),entry,field.getKey()));
    }
  }
  return results;
}
