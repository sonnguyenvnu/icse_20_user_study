@Override protected void onNewResultImpl(DataSource<CloseableReference<PooledByteBuffer>> dataSource){
  if (!dataSource.isFinished() || dataSource.getResult() == null) {
    return;
  }
  PooledByteBufferInputStream inputStream=null;
  FileOutputStream outputStream=null;
  try {
    inputStream=new PooledByteBufferInputStream(dataSource.getResult().get());
    outputStream=new FileOutputStream(mTempFile);
    IOUtils.copy(inputStream,outputStream);
    mFinished=true;
    onSuccess(mTempFile);
  }
 catch (  IOException e) {
    onFail(e);
  }
 finally {
    IOUtils.closeQuietly(inputStream);
    IOUtils.closeQuietly(outputStream);
  }
}
