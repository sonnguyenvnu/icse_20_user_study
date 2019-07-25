@Override public void unlock(Lock lock) throws IOException {
  locks.remove(lock.getSavepointId());
}
