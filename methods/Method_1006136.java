public static Optional<PreambleDiff> compare(BibDatabaseContext originalDatabase,BibDatabaseContext newDatabase){
  Optional<String> originalPreamble=originalDatabase.getDatabase().getPreamble();
  Optional<String> newPreamble=newDatabase.getDatabase().getPreamble();
  if (originalPreamble.equals(newPreamble)) {
    return Optional.empty();
  }
 else {
    return Optional.of(new PreambleDiff(originalPreamble.orElse(""),newPreamble.orElse("")));
  }
}
