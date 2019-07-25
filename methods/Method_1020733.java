@Override public ActivePlan0 activate(Map<Object,JoinObserver> externalSubscriptions,final Observer<R> observer,final Consumer<ActivePlan0> deactivate){
  Consumer<Throwable> onError=onErrorFrom(observer);
  final JoinObserver1<T1> jo1=createObserver(externalSubscriptions,expression.o1(),onError);
  final JoinObserver1<T2> jo2=createObserver(externalSubscriptions,expression.o2(),onError);
  final JoinObserver1<T3> jo3=createObserver(externalSubscriptions,expression.o3(),onError);
  final AtomicReference<ActivePlan3<T1,T2,T3>> self=new AtomicReference<ActivePlan3<T1,T2,T3>>();
  ActivePlan3<T1,T2,T3> activePlan=new ActivePlan3<T1,T2,T3>(jo1,jo2,jo3,new Consumer3<T1,T2,T3>(){
    @Override public void accept(    T1 t1,    T2 t2,    T3 t3){
      R result;
      try {
        result=selector.apply(t1,t2,t3);
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
      jo1.removeActivePlan(ap);
      jo2.removeActivePlan(ap);
      jo3.removeActivePlan(ap);
      deactivate.accept(ap);
    }
  }
);
  self.set(activePlan);
  jo1.addActivePlan(activePlan);
  jo2.addActivePlan(activePlan);
  jo3.addActivePlan(activePlan);
  return activePlan;
}
