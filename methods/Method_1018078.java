public void clear(){
  try {
    Iterator<Node> grpItr=(Iterator<Node>)getNode().getNodes(GROUPS);
    while (grpItr.hasNext()) {
      Node group=grpItr.next();
      group.remove();
    }
    grpItr=(Iterator<Node>)getNode().getNodes(DEFAULT_GROUP);
    while (grpItr.hasNext()) {
      Node group=grpItr.next();
      group.remove();
    }
  }
 catch (  RepositoryException e) {
    throw new MetadataRepositoryException("Failed to clear the SLA obligation group nodes",e);
  }
}
