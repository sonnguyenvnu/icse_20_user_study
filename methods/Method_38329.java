public DbSqlBuilder match(final String expression){
  return addChunk(new MatchChunk(entityManager,expression));
}
