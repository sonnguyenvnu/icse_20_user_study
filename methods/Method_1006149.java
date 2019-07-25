@Override public List<FieldChange> cleanup(BibEntry entry){
  List<FieldChange> changes=new ArrayList<>();
  for (  String field : Arrays.asList(FieldName.URL,FieldName.JOURNAL,FieldName.JOURNALTITLE,FieldName.NOTE)) {
    Optional<ArXivIdentifier> arXivIdentifier=entry.getField(field).flatMap(ArXivIdentifier::parse);
    if (arXivIdentifier.isPresent()) {
      entry.setField(FieldName.EPRINT,arXivIdentifier.get().getNormalized()).ifPresent(changes::add);
      entry.setField(FieldName.EPRINTTYPE,"arxiv").ifPresent(changes::add);
      arXivIdentifier.get().getClassification().ifPresent(classification -> entry.setField(FieldName.EPRINTCLASS,classification).ifPresent(changes::add));
      entry.clearField(field).ifPresent(changes::add);
      if (field.equals(FieldName.URL)) {
        entry.clearField(FieldName.URLDATE).ifPresent(changes::add);
      }
    }
  }
  return changes;
}
