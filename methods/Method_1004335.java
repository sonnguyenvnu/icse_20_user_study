@Override public void execute(@NotNull Callback<T> callback) throws ApolloCanceledException {
  checkNotNull(callback,"callback == null");
synchronized (this) {
switch (state.get()) {
case IDLE:
{
        state.set(ACTIVE);
        subscriptionCallback=new SubscriptionManagerCallback<>(callback,this);
        subscriptionManager.subscribe(subscription,subscriptionCallback);
        break;
      }
case CANCELED:
    throw new ApolloCanceledException("Call is cancelled.");
case TERMINATED:
case ACTIVE:
  throw new IllegalStateException("Already Executed");
default :
throw new IllegalStateException("Unknown state");
}
}
}
