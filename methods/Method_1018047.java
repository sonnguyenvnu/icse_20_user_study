public List<T> find(String query){
  List<T> entities=new ArrayList<>();
  try {
    QueryResult result=JcrQueryUtil.query(getSession(),query);
    if (result != null) {
      NodeIterator nodeIterator=result.getNodes();
      while (nodeIterator.hasNext()) {
        Node node=nodeIterator.nextNode();
        T entity=constructEntity(node);
        entities.add(entity);
      }
    }
    return entities;
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Unable to findAll for Type : " + getNodeType(getJcrEntityClass()),e);
  }
}
