private void _XXXXX_(ArtifactReference artifact,String path){
  ProjectReference projectRef=new ProjectReference();
  projectRef.setGroupId(artifact.getGroupId());
  projectRef.setArtifactId(artifact.getArtifactId());
  try {
    String metadataPath=this.metadataTools.toPath(projectRef);
    Path projectMetadata=this.repositoryDir.resolve(metadataPath);
    if (Files.exists(projectMetadata) && (Files.getLastModifiedTime(projectMetadata).toMillis() >= this.scanStartTimestamp)) {
      log.debug("Skipping uptodate metadata: {}",this.metadataTools.toPath(projectRef));
      return;
    }
    metadataTools.updateMetadata(this.repository,metadataPath);
    log.debug("Updated metadata: {}",this.metadataTools.toPath(projectRef));
  }
 catch (  RepositoryMetadataException e) {
    log.error("Unable to write project metadat for artifact [{}]:",path,e);
    triggerConsumerError(TYPE_METADATA_WRITE_FAILURE,"Unable to write project metadata for artifact [" + path + "]: "+ e.getMessage());
  }
catch (  IOException e) {
    log.warn("Project metadata not written due to IO warning: ",e);
    triggerConsumerWarning(TYPE_METADATA_IO,"Project metadata not written due to IO warning: " + e.getMessage());
  }
}