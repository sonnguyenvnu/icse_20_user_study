@Override protected void onFailureImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource){
  mFinished=true;
  onFail(new RuntimeException("onFailureImpl"));
}
