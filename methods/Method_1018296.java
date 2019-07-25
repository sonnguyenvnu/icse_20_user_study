@Override public void run(RunNotifier notifier){
  notifier.addListener(JUnitExecutionListener.getRunListener());
  super.run(notifier);
}
