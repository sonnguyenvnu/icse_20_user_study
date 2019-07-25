@Override public ResultSet<T> retrieve(Query<WrappedByteArray> query,QueryOptions options){
  ResultSet<WrappedByteArray> resultSet=index.retrieve(query,options);
  return new WrappedResultSet<T>(resultSet){
    @Override public Iterator<T> iterator(){
      return Iterables.filter(Iterables.transform(resultSet,AbstractIndex.this::getObject),Objects::nonNull).iterator();
    }
  }
;
}
