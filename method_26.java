@Override public UiConfiguration _XXXXX_() throws ArchivaRestServiceException {
  try {
    return archivaAdministration.getUiConfiguration();
  }
 catch (  RepositoryAdminException e) {
    throw new ArchivaRestServiceException(e.getMessage(),e);
  }
}