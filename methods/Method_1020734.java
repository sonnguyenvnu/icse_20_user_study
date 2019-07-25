@Override public ActivePlan0 activate(Map<Object,JoinObserver> externalSubscriptions,final Observer<R> observer,final Consumer<ActivePlan0> deactivate){
  Consumer<Throwable> onError=onErrorFrom(observer);
  final List<JoinObserver1<? extends Object>> observers=new ArrayList<JoinObserver1<? extends Object>>();
  for (int i=0; i < expression.size(); i++) {
    observers.add(createObserver(externalSubscriptions,expression.get(i),onError));
  }
  final AtomicReference<ActivePlanN> self=new AtomicReference<ActivePlanN>();
  ActivePlanN activePlan=new ActivePlanN(observers,new Consumer<Object[]>(){
    @Override public void accept(    Object[] args){
      R result;
      try {
        result=selector.apply(args);
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
      for (      JoinObserver1<? extends Object> jo : observers) {
        jo.removeActivePlan(self.get());
      }
      deactivate.accept(self.get());
    }
  }
);
  self.set(activePlan);
  for (  JoinObserver1<? extends Object> jo : observers) {
    jo.addActivePlan(activePlan);
  }
  return activePlan;
}
