private void unlock(){
  list.getReadWriteLock().writeLock().unlock();
}
