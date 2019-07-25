private void fail(Throwable cause){
  contentList.forEach(ReferenceCountUtil::safeRelease);
  contentList.clear();
  onFailure();
  future.completeExceptionally(cause);
}
