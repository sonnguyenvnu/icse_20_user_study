@Override public void dispose(){
  CustomViewersManager manager=CustomViewersManager.getInstance();
  if (manager != null) {
    for (    ValueWrapperFactory factory : ListSequence.fromList(CustomViewersInitializer_AppPluginPart.this.myFactories).reversedList()) {
      manager.removeFactory(factory);
    }
  }
 else   if (ListSequence.fromList(CustomViewersInitializer_AppPluginPart.this.myFactories).isNotEmpty()) {
    if (LOG.isEnabledFor(Level.ERROR)) {
      LOG.error("Cant find custom viewers manager while myFactories nonempty: " + CustomViewersInitializer_AppPluginPart.this.myFactories);
    }
  }
  ListSequence.fromList(CustomViewersInitializer_AppPluginPart.this.myFactories).clear();
}
