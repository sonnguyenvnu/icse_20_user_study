@Override public void dispose(){
  myCachedLanguages=null;
  myNamespaceIndices.clear();
  myLanguageRegistry.removeRegistryListener(this);
  INSTANCE=null;
}
