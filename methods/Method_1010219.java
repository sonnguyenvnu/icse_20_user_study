@Override public void check(O toCheck,SRepository repository,Consumer<? super I> errorCollector,ProgressMonitor monitor){
  try {
    myOrigin.check(toCheck,repository,errorCollector,monitor);
  }
 catch (  Exception e) {
    if (LOG.isEnabledFor(Level.ERROR)) {
      LOG.error(myMessage.invoke(toCheck,e,repository),e);
    }
  }
}
