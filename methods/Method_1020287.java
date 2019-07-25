@Override public int size(){
synchronized (selected) {
    return selected.size();
  }
}
