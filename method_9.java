@Override public void _XXXXX_(int rc,long ledgerId,long entryId,BookieSocketAddress addr,Object ctx){
  if (BookieProtocol.EOK == rc) {
    requestProcessor.getRequestStats().getAddEntryStats().registerSuccessfulEvent(MathUtils.elapsedNanos(startTimeNanos),TimeUnit.NANOSECONDS);
  }
 else {
    requestProcessor.getRequestStats().getAddEntryStats().registerFailedEvent(MathUtils.elapsedNanos(startTimeNanos),TimeUnit.NANOSECONDS);
  }
  sendResponse(rc,ResponseBuilder.buildAddResponse(request),requestProcessor.getRequestStats().getAddRequestStats());
  request.recycle();
  recycle();
}