@Override public ActivePlan0 activate(Map<Object,JoinObserver> externalSubscriptions,final Observer<R> observer,final Consumer<ActivePlan0> deactivate){
  Consumer<Throwable> onError=onErrorFrom(observer);
  final JoinObserver1<T1> firstJoinObserver=createObserver(externalSubscriptions,expression.o1(),onError);
  final AtomicReference<ActivePlan1<T1>> self=new AtomicReference<ActivePlan1<T1>>();
  ActivePlan1<T1> activePlan=new ActivePlan1<T1>(firstJoinObserver,new Consumer<T1>(){
    @Override public void accept(    T1 t1){
      R result;
      try {
        result=selector.apply(t1);
      }
 catch (      Throwable t) {
        observer.onError(t);
        return;
      }
      observer.onNext(result);
    }
  }
,new Action(){
    @Override public void run() throws Throwable {
      ActivePlan0 ap=self.get();
      firstJoinObserver.removeActivePlan(ap);
      deactivate.accept(ap);
    }
  }
);
  self.set(activePlan);
  firstJoinObserver.addActivePlan(activePlan);
  return activePlan;
}
