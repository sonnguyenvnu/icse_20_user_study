private static void throwExceptionIfInterruptedOrCancelled(AtomicBoolean isCanceled) throws InterruptedException {
  if (Thread.interrupted() || (isCanceled != null && isCanceled.get())) {
    throw new InterruptedException();
  }
}
