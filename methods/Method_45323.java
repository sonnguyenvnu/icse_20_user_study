public static void withLock(final Lock lock,final Runnable runnable){
  lock.lock();
  try {
    runnable.run();
  }
  finally {
    lock.unlock();
  }
}
