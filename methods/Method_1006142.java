/** 
 * Dredges up from the database file the field values for each entry in the list. It has no arguments. If a database entry doesn't have a value for a field (and probably no database entry will have a value for every field), that field variable is marked as missing for the entry. We use null for the missing entry designator.
 */
private void read(){
  for (  BstEntry e : entries) {
    for (    Map.Entry<String,String> mEntry : e.getFields().entrySet()) {
      String fieldValue=e.getBibtexEntry().getField(mEntry.getKey()).orElse(null);
      mEntry.setValue(fieldValue);
    }
  }
  for (  BstEntry e : entries) {
    if (!e.getFields().containsKey(FieldName.CROSSREF)) {
      e.getFields().put(FieldName.CROSSREF,null);
    }
  }
}
