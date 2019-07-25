@Override public SelectionCoreImpl regex(final String clause){
  return addToCurrentColumn(new SelectRegexClause(clause));
}
