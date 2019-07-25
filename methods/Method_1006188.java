@Override public List<IntegrityMessage> check(BibEntry entry){
  final List<String> allBiblatexOnlyFields=getAllBiblatexOnlyFields();
  return entry.getFieldNames().stream().filter(allBiblatexOnlyFields::contains).map(name -> new IntegrityMessage(Localization.lang("biblatex field only"),entry,name)).collect(Collectors.toList());
}
