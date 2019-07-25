@SuppressWarnings("ResultOfMethodCallIgnored") private synchronized void activate(Optional<Callback<T>> callback){
switch (state.get()) {
case IDLE:
    originalCallback.set(callback.orNull());
  tracker.registerCall(this);
callback.apply(new Action<Callback<T>>(){
  @Override public void apply(  @NotNull Callback<T> callback){
    callback.onStatusEvent(StatusEvent.SCHEDULED);
  }
}
);
break;
case CANCELED:
throw new ApolloCanceledException("Call is cancelled.");
case TERMINATED:
case ACTIVE:
throw new IllegalStateException("Already Executed");
default :
throw new IllegalStateException("Unknown state");
}
state.set(ACTIVE);
}
