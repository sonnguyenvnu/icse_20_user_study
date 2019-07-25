void unregister(@NotNull RepositoryVirtualFiles repoFiles){
  MyRepositoryListener listener;
synchronized (myRepoVFLock) {
    myPerRepositoryFiles.remove(repoFiles);
    listener=myFiles2ListenerMap.remove(repoFiles);
  }
  if (listener != null) {
    new RepoListenerRegistrar(repoFiles.getRepository(),listener).detach();
  }
}
