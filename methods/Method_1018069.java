private Stream<Node> find(String query){
  try {
    QueryResult result=JcrQueryUtil.query(JcrMetadataAccess.getActiveSession(),query);
    if (result != null) {
      @SuppressWarnings("unchecked") Iterator<Node> itr=result.getNodes();
      Iterable<Node> iterable=() -> itr;
      return StreamSupport.stream(iterable.spliterator(),false);
    }
 else {
      return Stream.empty();
    }
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Unable to query for role memberships",e);
  }
}
