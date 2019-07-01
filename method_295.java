public ArchivaRepositoryMetadata _XXXXX_(ManagedRepositoryContent managedRepository,ProjectReference reference,String proxyId){
  String metadataPath=getRepositorySpecificName(proxyId,toPath(reference));
  Path metadataFile=Paths.get(managedRepository.getRepoRoot(),metadataPath);
  if (!Files.exists(metadataFile) || !Files.isRegularFile(metadataFile)) {
    return null;
  }
  try {
    return MavenMetadataReader.read(metadataFile);
  }
 catch (  XMLException e) {
    log.warn("Unable to read metadata: {}",metadataFile.toAbsolutePath(),e);
    return null;
  }
}