@Override public List<IntegrityMessage> check(BibEntry entry){
  List<IntegrityMessage> result=new ArrayList<>();
  for (  Entry<String,String> field : entry.getFieldMap().entrySet()) {
    Set<FieldProperty> properties=InternalBibtexFields.getFieldProperties(field.getKey());
    if (properties.contains(FieldProperty.SINGLE_ENTRY_LINK)) {
      if (!database.getEntryByKey(field.getValue()).isPresent()) {
        result.add(new IntegrityMessage(Localization.lang("Referenced BibTeX key does not exist"),entry,field.getKey()));
      }
    }
 else     if (properties.contains(FieldProperty.MULTIPLE_ENTRY_LINK)) {
      List<String> keys=new ArrayList<>(Arrays.asList(field.getValue().split(",")));
      for (      String key : keys) {
        if (!database.getEntryByKey(key).isPresent()) {
          result.add(new IntegrityMessage(Localization.lang("Referenced BibTeX key does not exist") + ": " + key,entry,field.getKey()));
        }
      }
    }
  }
  return result;
}
