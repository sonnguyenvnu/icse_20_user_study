private Object getValue(Future future){
  if (future == null) {
    return null;
  }
  try {
    return future.get();
  }
 catch (  Exception e) {
    exceptions.add(e);
  }
  return null;
}
