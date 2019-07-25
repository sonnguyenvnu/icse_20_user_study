@Override public void check(O toCheck,SRepository repository,Consumer<? super I> errorCollector,ProgressMonitor monitor){
  IteratingChecker.IteratorWithProgress<P> iterator=myIterate.invoke(toCheck);
  monitor.start("",iterator.remainingSize());
  while (iterator.hasNext() && !(monitor.isCanceled())) {
    Tuples._2<P,Integer> next=iterator.nextItem();
    myOrigin.check(next._0(),repository,errorCollector,monitor.subTask((int)next._1(),SubProgressKind.IGNORED));
  }
  monitor.done();
}
