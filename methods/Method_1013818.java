@Override public void update(Object args,Observable observable){
  if (args instanceof NodeAdded<?>) {
    return;
  }
  if (args instanceof NodeDeleted<?>) {
    return;
  }
}
