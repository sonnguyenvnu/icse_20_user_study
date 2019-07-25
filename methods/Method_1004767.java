public SearchRequestBuilder scroll(long keepAliveMillis){
  Assert.isTrue(keepAliveMillis > 0,"Invalid scroll");
  this.scroll=TimeValue.timeValueMillis(keepAliveMillis);
  return this;
}
