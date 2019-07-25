public void dispose(){
  if (myModule != null) {
    Collection<SModel> models=new ArrayList<>(getModels());
    for (    SModel model : models) {
      myModule.unregisterModel((SModelBase)model);
    }
  }
  if (isRegistered()) {
    assert myModule != null;
    myModule.removeModuleListener(myModuleListener);
  }
  assert myModels.isEmpty();
  myRepository=null;
}
