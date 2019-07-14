private void onError(final Exception e){
  lastException=new DrmSessionException(e);
  eventDispatcher.dispatch(listener -> listener.onDrmSessionManagerError(e));
  if (state != STATE_OPENED_WITH_KEYS) {
    state=STATE_ERROR;
  }
}
