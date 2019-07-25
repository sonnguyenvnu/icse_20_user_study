@Override public void execute(DelegateExecution context) throws Exception {
  Semaphore s=semaphors.get(context.getBusinessKey());
  if (s != null) {
    s.release();
    semaphors.remove(context.getBusinessKey());
  }
}
