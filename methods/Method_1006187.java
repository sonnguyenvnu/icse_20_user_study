@Override public List<IntegrityMessage> check(BibEntry entry){
  Optional<String> value=entry.getField(field);
  if (!value.isPresent()) {
    return Collections.emptyList();
  }
  final String journal=value.get();
  if (!abbreviationRepository.isKnownName(journal)) {
    return Collections.singletonList(new IntegrityMessage(Localization.lang("journal not found in abbreviation list"),entry,field));
  }
  return Collections.emptyList();
}
