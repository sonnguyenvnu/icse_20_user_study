/** 
 * Begins the startup procedure by calling  {@link #doStart(Object)}, ensuring that neither {@link #doStart(Object)} nor {@link #doStop(Object)} is invoked concurrently. When the startup fails,{@link #stop(Object)} will be invoked with the specified {@code rollbackArg} automatically to roll backthe side effect caused by this method and any exceptions that occurred during the rollback will be reported to  {@link #rollbackFailed(Throwable)}.
 * @param arg           the argument to pass to {@link #doStart(Object)}, or  {@code null} to pass no argument.
 * @param rollbackArg   the argument to pass to {@link #doStop(Object)} when rolling back.
 * @param failIfStarted whether to fail the returned {@link CompletableFuture} withan  {@link IllegalStateException} when the startup procedure is alreadyin progress or done
 */
public final synchronized CompletableFuture<V> start(@Nullable T arg,@Nullable U rollbackArg,boolean failIfStarted){
switch (state) {
case STARTING:
case STARTED:
    if (failIfStarted) {
      return exceptionallyCompletedFuture(new IllegalStateException("must be stopped to start; currently " + state));
    }
 else {
      @SuppressWarnings("unchecked") final CompletableFuture<V> castFuture=(CompletableFuture<V>)future;
      return castFuture;
    }
case STOPPING:
  return future.exceptionally(unused -> null).thenComposeAsync(unused -> start(arg,failIfStarted),executor);
}
assert state == State.STOPPED : "state: " + state;
state=State.STARTING;
final CompletableFuture<V> startFuture=new CompletableFuture<>();
boolean submitted=false;
try {
executor.execute(() -> {
  try {
    notifyListeners(State.STARTING,arg,null,null);
    final CompletionStage<V> f=doStart(arg);
    if (f == null) {
      throw new IllegalStateException("doStart() returned null.");
    }
    f.handle((result,cause) -> {
      if (cause != null) {
        startFuture.completeExceptionally(cause);
      }
 else {
        startFuture.complete(result);
      }
      return null;
    }
);
  }
 catch (  Exception e) {
    startFuture.completeExceptionally(e);
  }
}
);
submitted=true;
}
 catch (Exception e) {
return exceptionallyCompletedFuture(e);
}
 finally {
if (!submitted) {
  state=State.STOPPED;
}
}
final CompletableFuture<V> future=startFuture.handleAsync((result,cause) -> {
if (cause != null) {
  final CompletableFuture<Void> rollbackFuture=stop(rollbackArg,true).exceptionally(stopCause -> {
    rollbackFailed(Exceptions.peel(stopCause));
    return null;
  }
);
  return rollbackFuture.<V>thenCompose(unused -> exceptionallyCompletedFuture(cause));
}
 else {
  enter(State.STARTED,arg,null,result);
  return completedFuture(result);
}
}
,executor).thenCompose(Function.identity());
this.future=future;
return future;
}
