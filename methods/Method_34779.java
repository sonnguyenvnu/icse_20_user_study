public void collapserResponseFromCache(HystrixCollapserKey collapserKey){
  HystrixCollapserEvent collapserEvent=HystrixCollapserEvent.from(collapserKey,HystrixEventType.Collapser.RESPONSE_FROM_CACHE,1);
  writeOnlyCollapserSubject.onNext(collapserEvent);
}
