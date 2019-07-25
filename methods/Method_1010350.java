@Override public void dispose(){
  myLanguageRegistry.removeRegistryListener(this);
  INSTANCE=null;
}
