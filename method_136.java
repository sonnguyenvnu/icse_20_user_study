@Override public ManagedRepository _XXXXX_(String repositoryId) throws RepositoryAdminException {
  return convertRepo(repositoryRegistry.getManagedRepository(repositoryId));
}