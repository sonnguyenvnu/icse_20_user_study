public void dispose(){
  if (myRepositoryRegistry != null) {
    myRepositoryRegistry.removeRepository(this);
  }
}
