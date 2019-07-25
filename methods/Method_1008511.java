IndexEventListener freeze(){
  if (this.frozen.compareAndSet(false,true)) {
    return new CompositeIndexEventListener(indexSettings,indexEventListeners);
  }
 else {
    throw new IllegalStateException("already frozen");
  }
}
