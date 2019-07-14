public static void throwIfInterrupted() throws InterruptedException {
  if (Thread.interrupted()) {
    throw new InterruptedException();
  }
}
