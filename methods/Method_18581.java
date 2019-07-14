public static void assertDoesntHoldLock(Object lock){
  if (!ComponentsConfiguration.IS_INTERNAL_BUILD) {
    return;
  }
  if (Thread.holdsLock(lock)) {
    throw new IllegalStateException("This method should be called outside the lock.");
  }
}
