@Override public void dispose(){
  myLanguageRegistry.removeRegistryListener(myLanguageRegistryListener);
  INSTANCE=null;
}
