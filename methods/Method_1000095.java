@Override public ResultSet<T> retrieve(Query<WrappedByteArray> query){
  ResultSet<WrappedByteArray> resultSet=index.retrieve(query);
  return new WrappedResultSet<T>(resultSet){
    @Override public Iterator<T> iterator(){
      return Iterables.filter(Iterables.transform(resultSet,AbstractIndex.this::getObject),Objects::nonNull).iterator();
    }
  }
;
}
