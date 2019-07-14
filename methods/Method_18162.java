private @Nullable static <T>EventHandler<T> addVisibilityHandler(@Nullable EventHandler<T> currentHandler,@Nullable EventHandler<T> newHandler){
  if (currentHandler == null) {
    return newHandler;
  }
  if (newHandler == null) {
    return currentHandler;
  }
  return new DelegatingEventHandler<>(currentHandler,newHandler);
}
