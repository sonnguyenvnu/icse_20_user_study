private void removeRunningWriters(){
  Iterator<T> iterator=mRunningWriters.iterator();
  while (iterator.hasNext()) {
    T writer=iterator.next();
    writer.onDestroy();
    iterator.remove();
  }
}
