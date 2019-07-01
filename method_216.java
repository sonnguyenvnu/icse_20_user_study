protected void _XXXXX_(ProxyConnector proxyConnector) throws RepositoryAdminException {
  if (repositoryRegistry.getManagedRepository(proxyConnector.getSourceRepoId()) == null) {
    throw new RepositoryAdminException("non valid ProxyConnector sourceRepo with id " + proxyConnector.getSourceRepoId() + " is not a ManagedRepository");
  }
  if (repositoryRegistry.getRemoteRepository(proxyConnector.getTargetRepoId()) == null) {
    throw new RepositoryAdminException("non valid ProxyConnector sourceRepo with id " + proxyConnector.getTargetRepoId() + " is not a RemoteRepository");
  }
}