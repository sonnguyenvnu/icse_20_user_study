@Override public void observe(@NonNull LifecycleOwner owner,@NonNull Observer<T> observer){
  if (owner.getLifecycle().getCurrentState() == DESTROYED) {
    return;
  }
  try {
    LifecycleBoundObserver wrapper=new ExternalLifecycleBoundObserver(owner,observer);
    LifecycleBoundObserver existing=(LifecycleBoundObserver)callMethodPutIfAbsent(observer,wrapper);
    if (existing != null && !existing.isAttachedTo(owner)) {
      throw new IllegalArgumentException("Cannot add the same observer" + " with different lifecycles");
    }
    if (existing != null) {
      return;
    }
    owner.getLifecycle().addObserver(wrapper);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
