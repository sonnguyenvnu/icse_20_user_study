private void migrate(BibEntry entry,ParserResult parserResult){
  if (hasReviewField(entry)) {
    updateFields(entry,mergeCommentFieldIfPresent(entry,entry.getField(FieldName.REVIEW).get()));
    parserResult.wasChangedOnMigration();
  }
}
