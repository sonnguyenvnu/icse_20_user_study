@Override public void dispose(){
  myLanguageRegistry.removeRegistryListener(myAspectReloadListener);
  removeAll();
}
