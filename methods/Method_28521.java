private void safelyUnregisterObserver(){
  try {
    if (registeredObserver && recyclerView.getAdapter() != null) {
      recyclerView.getAdapter().unregisterAdapterDataObserver(observer);
    }
  }
 catch (  Exception ignored) {
  }
}
