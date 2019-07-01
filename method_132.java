private void _XXXXX_(Path versionMetaDataFileInTargetRepo,ArtifactMetadata artifactMetadata,Date lastUpdatedTimestamp) throws RepositoryMetadataException {
  ArchivaRepositoryMetadata versionMetadata=getMetadata(versionMetaDataFileInTargetRepo);
  if (!Files.exists(versionMetaDataFileInTargetRepo)) {
    versionMetadata.setGroupId(artifactMetadata.getNamespace());
    versionMetadata.setArtifactId(artifactMetadata.getProject());
    versionMetadata.setVersion(artifactMetadata.getProjectVersion());
  }
  versionMetadata.setLastUpdatedTimestamp(lastUpdatedTimestamp);
  RepositoryMetadataWriter.write(versionMetadata,versionMetaDataFileInTargetRepo);
}