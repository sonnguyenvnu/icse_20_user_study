@Override public List<IntegrityMessage> check(BibEntry entry){
  Optional<String> value=entry.getField(FieldName.PAGES);
  if (!value.isPresent()) {
    return Collections.emptyList();
  }
  if ("proceedings".equalsIgnoreCase(entry.getType())) {
    return Collections.singletonList(new IntegrityMessage(Localization.lang("wrong entry type as proceedings has page numbers"),entry,FieldName.PAGES));
  }
  return Collections.emptyList();
}
