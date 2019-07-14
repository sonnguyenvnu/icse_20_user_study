@Override public void injectIDManager(IDManager idManager){
  Preconditions.checkNotNull(idManager);
  this.idManager=idManager;
}
