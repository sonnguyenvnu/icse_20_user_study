public static DefaultExecution require() throws UnmanagedThreadException {
  DefaultExecution execution=ExecThreadBinding.require().getExecution();
  if (execution == null) {
    throw new IllegalStateException("No execution bound for thread " + Thread.currentThread().getName());
  }
 else {
    return execution;
  }
}
