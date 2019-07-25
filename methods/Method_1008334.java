@Override public void register(InjectionListener<? super T> injectionListener){
  if (!valid) {
    throw new IllegalStateException("Encounters may not be used after hear() returns.");
  }
  if (injectionListeners == null) {
    injectionListeners=new ArrayList<>();
  }
  injectionListeners.add(injectionListener);
}
