@Override public void _XXXXX_(MetadataRepository metadataRepository,String sourceRepoId,String targetRepoId) throws RepositoryMergerException {
  try {
    List<ArtifactMetadata> artifactsInSourceRepo=metadataRepository.getArtifacts(sourceRepoId);
    for (    ArtifactMetadata artifactMetadata : artifactsInSourceRepo) {
      artifactMetadata.setRepositoryId(targetRepoId);
      createFolderStructure(sourceRepoId,targetRepoId,artifactMetadata);
    }
  }
 catch (  MetadataRepositoryException e) {
    throw new RepositoryMergerException(e.getMessage(),e);
  }
catch (  IOException e) {
    throw new RepositoryMergerException(e.getMessage(),e);
  }
catch (  RepositoryException e) {
    throw new RepositoryMergerException(e.getMessage(),e);
  }
}