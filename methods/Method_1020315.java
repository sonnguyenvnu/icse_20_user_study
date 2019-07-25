static final void register(Loader obj){
  getInstance().liveObjects.put(new Long(obj.handle),new WeakReference(obj));
}
