/** 
 * Execute <code>getFallback()</code> within protection of a semaphore that limits number of concurrent executions. <p> Fallback implementations shouldn't perform anything that can be blocking, but we protect against it anyways in case someone doesn't abide by the contract. <p> If something in the <code>getFallback()</code> implementation is latent (such as a network call) then the semaphore will cause us to start rejecting requests rather than allowing potentially all threads to pile up and block.
 * @return K
 * @throws UnsupportedOperationException if getFallback() not implemented
 * @throws HystrixRuntimeException if getFallback() fails (throws an Exception) or is rejected by the semaphore
 */
private Observable<R> getFallbackOrThrowException(final AbstractCommand<R> _cmd,final HystrixEventType eventType,final FailureType failureType,final String message,final Exception originalException){
  final HystrixRequestContext requestContext=HystrixRequestContext.getContextForCurrentThread();
  long latency=System.currentTimeMillis() - executionResult.getStartTimestamp();
  executionResult=executionResult.addEvent((int)latency,eventType);
  if (isUnrecoverable(originalException)) {
    logger.error("Unrecoverable Error for HystrixCommand so will throw HystrixRuntimeException and not apply fallback. ",originalException);
    Exception e=wrapWithOnErrorHook(failureType,originalException);
    return Observable.error(new HystrixRuntimeException(failureType,this.getClass(),getLogMessagePrefix() + " " + message + " and encountered unrecoverable error.",e,null));
  }
 else {
    if (isRecoverableError(originalException)) {
      logger.warn("Recovered from java.lang.Error by serving Hystrix fallback",originalException);
    }
    if (properties.fallbackEnabled().get()) {
      final Action1<Notification<? super R>> setRequestContext=new Action1<Notification<? super R>>(){
        @Override public void call(        Notification<? super R> rNotification){
          setRequestContextIfNeeded(requestContext);
        }
      }
;
      final Action1<R> markFallbackEmit=new Action1<R>(){
        @Override public void call(        R r){
          if (shouldOutputOnNextEvents()) {
            executionResult=executionResult.addEvent(HystrixEventType.FALLBACK_EMIT);
            eventNotifier.markEvent(HystrixEventType.FALLBACK_EMIT,commandKey);
          }
        }
      }
;
      final Action0 markFallbackCompleted=new Action0(){
        @Override public void call(){
          long latency=System.currentTimeMillis() - executionResult.getStartTimestamp();
          eventNotifier.markEvent(HystrixEventType.FALLBACK_SUCCESS,commandKey);
          executionResult=executionResult.addEvent((int)latency,HystrixEventType.FALLBACK_SUCCESS);
        }
      }
;
      final Func1<Throwable,Observable<R>> handleFallbackError=new Func1<Throwable,Observable<R>>(){
        @Override public Observable<R> call(        Throwable t){
          Exception e=wrapWithOnErrorHook(failureType,originalException);
          Exception fe=getExceptionFromThrowable(t);
          long latency=System.currentTimeMillis() - executionResult.getStartTimestamp();
          Exception toEmit;
          if (fe instanceof UnsupportedOperationException) {
            logger.debug("No fallback for HystrixCommand. ",fe);
            eventNotifier.markEvent(HystrixEventType.FALLBACK_MISSING,commandKey);
            executionResult=executionResult.addEvent((int)latency,HystrixEventType.FALLBACK_MISSING);
            toEmit=new HystrixRuntimeException(failureType,_cmd.getClass(),getLogMessagePrefix() + " " + message + " and no fallback available.",e,fe);
          }
 else {
            logger.debug("HystrixCommand execution " + failureType.name() + " and fallback failed.",fe);
            eventNotifier.markEvent(HystrixEventType.FALLBACK_FAILURE,commandKey);
            executionResult=executionResult.addEvent((int)latency,HystrixEventType.FALLBACK_FAILURE);
            toEmit=new HystrixRuntimeException(failureType,_cmd.getClass(),getLogMessagePrefix() + " " + message + " and fallback failed.",e,fe);
          }
          if (shouldNotBeWrapped(originalException)) {
            return Observable.error(e);
          }
          return Observable.error(toEmit);
        }
      }
;
      final TryableSemaphore fallbackSemaphore=getFallbackSemaphore();
      final AtomicBoolean semaphoreHasBeenReleased=new AtomicBoolean(false);
      final Action0 singleSemaphoreRelease=new Action0(){
        @Override public void call(){
          if (semaphoreHasBeenReleased.compareAndSet(false,true)) {
            fallbackSemaphore.release();
          }
        }
      }
;
      Observable<R> fallbackExecutionChain;
      if (fallbackSemaphore.tryAcquire()) {
        try {
          if (isFallbackUserDefined()) {
            executionHook.onFallbackStart(this);
            fallbackExecutionChain=getFallbackObservable();
          }
 else {
            fallbackExecutionChain=getFallbackObservable();
          }
        }
 catch (        Throwable ex) {
          fallbackExecutionChain=Observable.error(ex);
        }
        return fallbackExecutionChain.doOnEach(setRequestContext).lift(new FallbackHookApplication(_cmd)).lift(new DeprecatedOnFallbackHookApplication(_cmd)).doOnNext(markFallbackEmit).doOnCompleted(markFallbackCompleted).onErrorResumeNext(handleFallbackError).doOnTerminate(singleSemaphoreRelease).doOnUnsubscribe(singleSemaphoreRelease);
      }
 else {
        return handleFallbackRejectionByEmittingError();
      }
    }
 else {
      return handleFallbackDisabledByEmittingError(originalException,failureType,message);
    }
  }
}
