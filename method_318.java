private CompletableFuture<GetActiveRangesResponse> _XXXXX_(long streamId,MetaRangeImpl metaRange,StreamProperties streamProps){
  if (null == streamProps) {
    return FutureUtils.value(GetActiveRangesResponse.newBuilder().setCode(StatusCode.STREAM_NOT_FOUND).build());
  }
  return metaRange.create(streamProps).thenCompose(created -> {
    if (created) {
synchronized (streams) {
        streams.put(streamId,metaRange);
      }
      return getActiveRanges(metaRange);
    }
 else {
      return FutureUtils.value(GetActiveRangesResponse.newBuilder().setCode(StatusCode.INTERNAL_SERVER_ERROR).build());
    }
  }
);
}