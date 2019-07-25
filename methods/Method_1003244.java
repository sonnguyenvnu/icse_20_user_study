private static <T>T complete(Future<T> future) throws IOException {
  boolean interrupted=false;
  for (; ; ) {
    try {
      T result=future.get();
      if (interrupted) {
        Thread.currentThread().interrupt();
      }
      return result;
    }
 catch (    InterruptedException e) {
      interrupted=true;
    }
catch (    ExecutionException e) {
      throw new IOException(e.getCause());
    }
  }
}
