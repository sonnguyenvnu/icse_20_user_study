@SuppressWarnings("rawtypes") static SavedHooks enable(boolean chain){
  PlainConsumer<ProtocolNonConformanceException> h=onViolation;
  if (h == null) {
    h=DEFAULT;
  }
  final PlainConsumer<ProtocolNonConformanceException> handler=h;
  final Function<? super Completable,? extends Completable> saveC=RxJavaPlugins.getOnCompletableAssembly();
  Function<? super Completable,? extends Completable> oldCompletable=saveC;
  if (oldCompletable == null || !chain) {
    oldCompletable=Functions.identity();
  }
  final Function<? super Completable,? extends Completable> oldC=oldCompletable;
  RxJavaPlugins.setOnCompletableAssembly(new Function<Completable,Completable>(){
    @Override public Completable apply(    Completable c) throws Throwable {
      return oldC.apply(new CompletableValidator(c,handler));
    }
  }
);
  final Function<? super Maybe,? extends Maybe> saveM=RxJavaPlugins.getOnMaybeAssembly();
  Function<? super Maybe,? extends Maybe> oldMaybe=saveM;
  if (oldMaybe == null || !chain) {
    oldMaybe=Functions.identity();
  }
  final Function<? super Maybe,? extends Maybe> oldM=oldMaybe;
  RxJavaPlugins.setOnMaybeAssembly(new Function<Maybe,Maybe>(){
    @SuppressWarnings("unchecked") @Override public Maybe apply(    Maybe c) throws Throwable {
      return oldM.apply(new MaybeValidator(c,handler));
    }
  }
);
  final Function<? super Single,? extends Single> saveS=RxJavaPlugins.getOnSingleAssembly();
  Function<? super Single,? extends Single> oldSingle=saveS;
  if (oldSingle == null || !chain) {
    oldSingle=Functions.identity();
  }
  final Function<? super Single,? extends Single> oldS=oldSingle;
  RxJavaPlugins.setOnSingleAssembly(new Function<Single,Single>(){
    @SuppressWarnings("unchecked") @Override public Single apply(    Single c) throws Throwable {
      return oldS.apply(new SingleValidator(c,handler));
    }
  }
);
  final Function<? super Observable,? extends Observable> saveO=RxJavaPlugins.getOnObservableAssembly();
  Function<? super Observable,? extends Observable> oldObservable=saveO;
  if (oldObservable == null || !chain) {
    oldObservable=Functions.identity();
  }
  final Function<? super Observable,? extends Observable> oldO=oldObservable;
  RxJavaPlugins.setOnObservableAssembly(new Function<Observable,Observable>(){
    @SuppressWarnings("unchecked") @Override public Observable apply(    Observable c) throws Throwable {
      return oldO.apply(new ObservableValidator(c,handler));
    }
  }
);
  final Function<? super Flowable,? extends Flowable> saveF=RxJavaPlugins.getOnFlowableAssembly();
  Function<? super Flowable,? extends Flowable> oldFlowable=saveF;
  if (oldFlowable == null || !chain) {
    oldFlowable=Functions.identity();
  }
  final Function<? super Flowable,? extends Flowable> oldF=oldFlowable;
  RxJavaPlugins.setOnFlowableAssembly(new Function<Flowable,Flowable>(){
    @SuppressWarnings("unchecked") @Override public Flowable apply(    Flowable c) throws Throwable {
      return oldF.apply(new FlowableValidator(c,handler));
    }
  }
);
  final Function<? super ConnectableFlowable,? extends ConnectableFlowable> saveCF=RxJavaPlugins.getOnConnectableFlowableAssembly();
  Function<? super ConnectableFlowable,? extends ConnectableFlowable> oldConnFlow=saveCF;
  if (oldConnFlow == null || !chain) {
    oldConnFlow=Functions.identity();
  }
  final Function<? super ConnectableFlowable,? extends ConnectableFlowable> oldCF=oldConnFlow;
  RxJavaPlugins.setOnConnectableFlowableAssembly(new Function<ConnectableFlowable,ConnectableFlowable>(){
    @SuppressWarnings("unchecked") @Override public ConnectableFlowable apply(    ConnectableFlowable c) throws Throwable {
      return oldCF.apply(new ConnectableFlowableValidator(c,handler));
    }
  }
);
  final Function<? super ConnectableObservable,? extends ConnectableObservable> saveCO=RxJavaPlugins.getOnConnectableObservableAssembly();
  Function<? super ConnectableObservable,? extends ConnectableObservable> oldConnObs=saveCO;
  if (oldConnObs == null || !chain) {
    oldConnObs=Functions.identity();
  }
  final Function<? super ConnectableObservable,? extends ConnectableObservable> oldCO=oldConnObs;
  RxJavaPlugins.setOnConnectableObservableAssembly(new Function<ConnectableObservable,ConnectableObservable>(){
    @SuppressWarnings("unchecked") @Override public ConnectableObservable apply(    ConnectableObservable c) throws Throwable {
      return oldCO.apply(new ConnectableObservableValidator(c,handler));
    }
  }
);
  final Function<? super ParallelFlowable,? extends ParallelFlowable> savePF=RxJavaPlugins.getOnParallelAssembly();
  Function<? super ParallelFlowable,? extends ParallelFlowable> oldParFlow=savePF;
  if (oldParFlow == null || !chain) {
    oldParFlow=Functions.identity();
  }
  final Function<? super ParallelFlowable,? extends ParallelFlowable> oldPF=oldParFlow;
  RxJavaPlugins.setOnParallelAssembly(new Function<ParallelFlowable,ParallelFlowable>(){
    @SuppressWarnings("unchecked") @Override public ParallelFlowable apply(    ParallelFlowable c) throws Throwable {
      return oldPF.apply(new ParallelFlowableValidator(c,handler));
    }
  }
);
  enabled=true;
  return new SavedHooks(){
    @Override public void restore(){
      RxJavaPlugins.setOnCompletableAssembly(saveC);
      RxJavaPlugins.setOnSingleAssembly(saveS);
      RxJavaPlugins.setOnMaybeAssembly(saveM);
      RxJavaPlugins.setOnObservableAssembly(saveO);
      RxJavaPlugins.setOnFlowableAssembly(saveF);
      RxJavaPlugins.setOnConnectableObservableAssembly(saveCO);
      RxJavaPlugins.setOnConnectableFlowableAssembly(saveCF);
      RxJavaPlugins.setOnParallelAssembly(savePF);
    }
  }
;
}
