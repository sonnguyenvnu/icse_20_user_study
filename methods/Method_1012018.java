public void unregister(@NotNull IChangeListener l){
  myNodeListeners.remove(l);
  myModelListeners.remove(l);
  myModuleListeners.remove(l);
}
