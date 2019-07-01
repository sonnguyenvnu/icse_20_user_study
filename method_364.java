@Override public Boolean _XXXXX_(String fileLocation) throws ArchivaRestServiceException {
  String location=repositoryCommonValidator.removeExpressions(fileLocation);
  return Files.exists(Paths.get(location));
}