@Override public List<FieldChange> cleanup(BibEntry entry){
  List<FieldChange> changes=new ArrayList<>();
  if (entry.hasField(FieldName.DOI)) {
    String doiFieldValue=entry.getField(FieldName.DOI).orElse(null);
    Optional<DOI> doi=DOI.parse(doiFieldValue);
    if (doi.isPresent()) {
      String newValue=doi.get().getDOI();
      if (!doiFieldValue.equals(newValue)) {
        entry.setField(FieldName.DOI,newValue);
        FieldChange change=new FieldChange(entry,FieldName.DOI,doiFieldValue,newValue);
        changes.add(change);
      }
      for (      String field : FIELDS) {
        entry.getField(field).flatMap(DOI::parse).ifPresent(unused -> removeFieldValue(entry,field,changes));
      }
    }
  }
 else {
    for (    String field : FIELDS) {
      Optional<DOI> doi=entry.getField(field).flatMap(DOI::parse);
      if (doi.isPresent()) {
        Optional<FieldChange> change=entry.setField(FieldName.DOI,doi.get().getDOI());
        change.ifPresent(changes::add);
        removeFieldValue(entry,field,changes);
      }
    }
  }
  return changes;
}
