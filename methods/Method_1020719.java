/** 
 * Enable the assembly tracking.
 */
@SuppressWarnings({"unchecked","rawtypes"}) public static void enable(){
  if (lock.compareAndSet(false,true)) {
    RxJavaPlugins.setOnFlowableAssembly(new Function<Flowable,Flowable>(){
      @Override public Flowable apply(      Flowable f) throws Exception {
        if (f instanceof Supplier) {
          if (f instanceof ScalarSupplier) {
            return new FlowableOnAssemblyScalarSupplier(f);
          }
          return new FlowableOnAssemblySupplier(f);
        }
        return new FlowableOnAssembly(f);
      }
    }
);
    RxJavaPlugins.setOnConnectableFlowableAssembly(new Function<ConnectableFlowable,ConnectableFlowable>(){
      @Override public ConnectableFlowable apply(      ConnectableFlowable f) throws Exception {
        return new FlowableOnAssemblyConnectable(f);
      }
    }
);
    RxJavaPlugins.setOnObservableAssembly(new Function<Observable,Observable>(){
      @Override public Observable apply(      Observable f) throws Exception {
        if (f instanceof Supplier) {
          if (f instanceof ScalarSupplier) {
            return new ObservableOnAssemblyScalarSupplier(f);
          }
          return new ObservableOnAssemblySupplier(f);
        }
        return new ObservableOnAssembly(f);
      }
    }
);
    RxJavaPlugins.setOnConnectableObservableAssembly(new Function<ConnectableObservable,ConnectableObservable>(){
      @Override public ConnectableObservable apply(      ConnectableObservable f) throws Exception {
        return new ObservableOnAssemblyConnectable(f);
      }
    }
);
    RxJavaPlugins.setOnSingleAssembly(new Function<Single,Single>(){
      @Override public Single apply(      Single f) throws Exception {
        if (f instanceof Supplier) {
          if (f instanceof ScalarSupplier) {
            return new SingleOnAssemblyScalarSupplier(f);
          }
          return new SingleOnAssemblySupplier(f);
        }
        return new SingleOnAssembly(f);
      }
    }
);
    RxJavaPlugins.setOnCompletableAssembly(new Function<Completable,Completable>(){
      @Override public Completable apply(      Completable f) throws Exception {
        if (f instanceof Supplier) {
          if (f instanceof ScalarSupplier) {
            return new CompletableOnAssemblyScalarSupplier(f);
          }
          return new CompletableOnAssemblySupplier(f);
        }
        return new CompletableOnAssembly(f);
      }
    }
);
    RxJavaPlugins.setOnMaybeAssembly(new Function<Maybe,Maybe>(){
      @Override public Maybe apply(      Maybe f) throws Exception {
        if (f instanceof Supplier) {
          if (f instanceof ScalarSupplier) {
            return new MaybeOnAssemblyScalarSupplier(f);
          }
          return new MaybeOnAssemblySupplier(f);
        }
        return new MaybeOnAssembly(f);
      }
    }
);
    RxJavaPlugins.setOnParallelAssembly(new Function<ParallelFlowable,ParallelFlowable>(){
      @Override public ParallelFlowable apply(      ParallelFlowable t) throws Exception {
        return new ParallelFlowableOnAssembly(t);
      }
    }
);
    lock.set(false);
  }
}
