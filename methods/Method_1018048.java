private void reindex(MetadataAccess metadata){
  metadata.commit(() -> {
    Session session=JcrMetadataAccess.getActiveSession();
    Workspace workspace=(Workspace)session.getWorkspace();
    workspace.reindex();
  }
,MetadataAccess.SERVICE);
}
