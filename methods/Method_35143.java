final void executeWithRouter(@NonNull RouterRequiringFunc listener){
  if (router != null) {
    listener.execute();
  }
 else {
    onRouterSetListeners.add(listener);
  }
}
