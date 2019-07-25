private void configure(final Callback<None> callback,final PublisherWithStatus<T> activate,final PublisherWithStatus<T> deactivate){
  activate.start(new Callback<None>(){
    @Override public void onSuccess(    None none){
      PropertyEventPublisher<T> pubActivate=activate.getPublisher();
      pubActivate.setBus(_eventBus);
      _eventBus.setPublisher(pubActivate);
      if (deactivate.started()) {
        PropertyEventPublisher<T> pubDeactivate=deactivate.getPublisher();
        pubDeactivate.setBus(_nullBus);
      }
      callback.onSuccess(None.none());
    }
    @Override public void onError(    Throwable e){
      callback.onError(e);
    }
  }
);
}
