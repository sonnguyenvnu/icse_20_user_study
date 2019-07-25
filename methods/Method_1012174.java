@MainThread public void observe(LifecycleOwner owner,final Observer<T> observer){
  if (hasActiveObservers()) {
    Log.w(TAG,"Multiple observers registered but only one will be notified of changes.");
  }
  super.observe(owner,new Observer<T>(){
    @Override public void onChanged(    @Nullable T t){
      if (mPending.compareAndSet(true,false)) {
        observer.onChanged(t);
      }
    }
  }
);
}
