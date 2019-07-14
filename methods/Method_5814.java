@Override public long open(DataSpec dataSpec) throws FileDataSourceException {
  try {
    uri=dataSpec.uri;
    transferInitializing(dataSpec);
    file=new RandomAccessFile(dataSpec.uri.getPath(),"r");
    file.seek(dataSpec.position);
    bytesRemaining=dataSpec.length == C.LENGTH_UNSET ? file.length() - dataSpec.position : dataSpec.length;
    if (bytesRemaining < 0) {
      throw new EOFException();
    }
  }
 catch (  IOException e) {
    throw new FileDataSourceException(e);
  }
  opened=true;
  transferStarted(dataSpec);
  return bytesRemaining;
}
