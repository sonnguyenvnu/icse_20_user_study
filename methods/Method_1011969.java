void register(@NotNull RepositoryVirtualFiles repoFiles){
  MyRepositoryListener listener;
synchronized (myRepoVFLock) {
    RepositoryVirtualFiles existing=findForRepository(repoFiles.getRepository());
    if (existing != null) {
      throw new IllegalArgumentException("Attempt to register another VirtualFile container for the same repository");
    }
    myPerRepositoryFiles.add(0,repoFiles);
    listener=new MyRepositoryListener(repoFiles);
    myFiles2ListenerMap.put(repoFiles,listener);
  }
  new RepoListenerRegistrar(repoFiles.getRepository(),listener).attach();
}
