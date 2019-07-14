private void fireTestFailures(List<RunListener> listeners,final List<Failure> failures){
  if (!failures.isEmpty()) {
    new SafeNotifier(listeners){
      @Override protected void notifyListener(      RunListener listener) throws Exception {
        for (        Failure each : failures) {
          listener.testFailure(each);
        }
      }
    }
.run();
  }
}
