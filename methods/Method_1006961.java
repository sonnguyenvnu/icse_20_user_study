@Override public void close(){
  List<RuntimeException> errors=new ArrayList<>();
  Set<Map.Entry<String,Set<Runnable>>> copy;
synchronized (callbacks) {
    copy=new HashSet<>(callbacks.entrySet());
  }
  for (  Map.Entry<String,Set<Runnable>> entry : copy) {
    for (    Runnable callback : entry.getValue()) {
      if (callback != null) {
        try {
          callback.run();
        }
 catch (        RuntimeException t) {
          errors.add(t);
        }
      }
    }
  }
  if (errors.isEmpty()) {
    return;
  }
  throw errors.get(0);
}
