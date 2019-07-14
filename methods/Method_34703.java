/** 
 * Used for asynchronous execution with a callback by subscribing to the  {@link Observable}. <p> This eagerly starts execution the same as  {@link HystrixCollapser#queue()} and {@link HystrixCollapser#execute()}. A lazy  {@link Observable} can be obtained from {@link #toObservable()}. <p> <b>Callback Scheduling</b> <p> <ul> <li>When using  {@link ExecutionIsolationStrategy#THREAD} this defaults to using {@link Schedulers#computation()} for callbacks.</li><li>When using  {@link ExecutionIsolationStrategy#SEMAPHORE} this defaults to using {@link Schedulers#immediate()} for callbacks.</li></ul> Use  {@link #toObservable(rx.Scheduler)} to schedule the callback differently.<p> See https://github.com/Netflix/RxJava/wiki for more information.
 * @return {@code Observable<R>} that executes and calls back with the result of of {@link HystrixCommand}{@code <BatchReturnType>} execution after mappingthe  {@code <BatchReturnType>} into {@code <ResponseType>}
 */
public Observable<ResponseType> observe(){
  ReplaySubject<ResponseType> subject=ReplaySubject.create();
  final Subscription underlyingSubscription=toObservable().subscribe(subject);
  return subject.doOnUnsubscribe(new Action0(){
    @Override public void call(){
      underlyingSubscription.unsubscribe();
    }
  }
);
}
