@Override public void dispose(){
  if (INSTANCE == null) {
    return;
  }
  INSTANCE=null;
  clearAll();
  myRepository.getModelAccess().runWriteAction(new Runnable(){
    @Override public void run(){
      myRepository.unregisterModule(ProjectStructureModule.this,myOwner);
    }
  }
);
  myRepository.removeRepositoryListener(myListener);
}
