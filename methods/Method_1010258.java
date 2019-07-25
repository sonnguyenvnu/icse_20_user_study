public void attach(@NotNull SRepository repo){
  if (myRepository == repo) {
    LOG.warn("The model " + this + " is already attached to the repository " + repo);
    return;
  }
  if (myRepository != null) {
    throw new IllegalModelAccessException("Model is already attached to a repository, can't attach to another one");
  }
  repo.getModelAccess().checkReadAccess();
  myRepository=repo;
  myModelEventDispatch.modelAttached(this,repo);
}
