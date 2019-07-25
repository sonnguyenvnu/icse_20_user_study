@Override public long count() throws RepositoryException {
  final StringBuilder sql=new StringBuilder("SELECT COUNT(" + JdbcRepositories.getDefaultKeyName() + ") FROM ").append(getName());
  return count(sql,new ArrayList<>());
}
