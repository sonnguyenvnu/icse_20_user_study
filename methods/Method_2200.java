@Override public void trackCloseableReferenceLeak(SharedReference<Object> reference,@Nullable Throwable stacktrace){
  if (mListener == null) {
    Log.w("FRESCO","No Flipper listener registered to track CloseableReference leak.");
    return;
  }
  mListener.onCloseableReferenceLeak(reference,stacktrace);
}
