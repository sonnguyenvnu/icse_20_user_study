@Override public List<IntegrityMessage> check(BibEntry entry){
  Optional<String> author=entry.getField(FieldName.AUTHOR);
  Optional<String> title=entry.getField(FieldName.TITLE);
  Optional<String> year=entry.getField(FieldName.YEAR);
  if (!author.isPresent() || !title.isPresent() || !year.isPresent()) {
    return Collections.emptyList();
  }
  if (StringUtil.isBlank(entry.getCiteKeyOptional())) {
    String authorTitleYear=entry.getAuthorTitleYear(100);
    return Collections.singletonList(new IntegrityMessage(Localization.lang("empty BibTeX key") + ": " + authorTitleYear,entry,BibEntry.KEY_FIELD));
  }
  return Collections.emptyList();
}
