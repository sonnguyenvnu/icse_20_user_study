@Override public List<String> _XXXXX_() throws ArchivaRestServiceException {
  try {
    return new ArrayList<>(archivaAdministration.getKnownContentConsumers());
  }
 catch (  RepositoryAdminException e) {
    throw new ArchivaRestServiceException(e.getMessage(),e);
  }
}