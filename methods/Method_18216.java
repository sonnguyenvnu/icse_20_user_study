static void dispatchOnFullImpression(EventHandler<FullImpressionVisibleEvent> fullImpressionHandler){
  assertMainThread();
  if (sFullImpressionVisibleEvent == null) {
    sFullImpressionVisibleEvent=new FullImpressionVisibleEvent();
  }
  fullImpressionHandler.dispatchEvent(sFullImpressionVisibleEvent);
}
