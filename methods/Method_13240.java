@Override public Collection<Type> make(API api,Container.Entry entry){
  Listener listener=getListener(entry);
  if (listener == null) {
    return Collections.emptyList();
  }
 else {
    return listener.getRootTypes();
  }
}
