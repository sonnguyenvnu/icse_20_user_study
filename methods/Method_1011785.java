@NotNull @Override protected AbstractInstaller.State install(final boolean dryRun){
  if (!(PluginUtil.isGitPluginEnabled())) {
    return AbstractInstaller.State.NOT_ENABLED;
  }
  Iterable<VcsRoot> gitRoots=getGitRoots();
  if (Sequence.fromIterable(gitRoots).isEmpty()) {
    return AbstractInstaller.State.INSTALLED;
  }
 else {
    List<AbstractInstaller.State> states=Sequence.fromIterable(gitRoots).select(new ISelector<VcsRoot,AbstractInstaller.State>(){
      public AbstractInstaller.State select(      VcsRoot r){
        return installForRootInWrite(r.getPath(),dryRun);
      }
    }
).toListSequence();
    if (ListSequence.fromList(states).all(new IWhereFilter<AbstractInstaller.State>(){
      public boolean accept(      AbstractInstaller.State s){
        return s == AbstractInstaller.State.INSTALLED;
      }
    }
)) {
      return AbstractInstaller.State.INSTALLED;
    }
 else     if (ListSequence.fromList(states).any(new IWhereFilter<AbstractInstaller.State>(){
      public boolean accept(      AbstractInstaller.State s){
        return s == AbstractInstaller.State.OUTDATED;
      }
    }
)) {
      if (dryRun) {
        return AbstractInstaller.State.OUTDATED;
      }
    }
 else {
      if (dryRun) {
        return AbstractInstaller.State.NOT_INSTALLED;
      }
    }
    return installForRoots(gitRoots);
  }
}
