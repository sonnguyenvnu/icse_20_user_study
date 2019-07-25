public SelectQueryImpl from(final String database,final String[] table){
  if (table == null) {
    throw new IllegalArgumentException("Tables names should be specified");
  }
  SelectQueryImpl selectQuery=new SelectQueryImpl(database,new MultipleFromClause(Arrays.asList(table)),requiresPost,selectionCore);
  return selectQuery;
}
