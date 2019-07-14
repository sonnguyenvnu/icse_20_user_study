public TestListener createAdaptingListener(final RunNotifier notifier){
  return new OldTestClassAdaptingListener(notifier);
}
