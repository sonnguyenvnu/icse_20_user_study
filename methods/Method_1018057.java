public void configure(){
  this.metadataAccess.commit(() -> {
    try {
      Session session=JcrMetadataAccess.getActiveSession();
      ensureLayout(session);
      ensureAccessControl(session);
      ensureIndexes(session);
    }
 catch (    RepositoryException e) {
      throw new MetadataRepositoryException("Could not create initial JCR metadata",e);
    }
  }
,MetadataAccess.SERVICE);
  this.configured.set(true);
  firePostConfigActions();
}
