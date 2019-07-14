@Override @GuardedBy("evictionLock") public void setNextInWriteOrder(@Nullable Node<K,V> next){
  throw new UnsupportedOperationException();
}
