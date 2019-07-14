@Override public long open(DataSpec dataSpec) throws EncryptedFileDataSourceException {
  try {
    uri=dataSpec.uri;
    File path=new File(dataSpec.uri.getPath());
    String name=path.getName();
    File keyPath=new File(FileLoader.getInternalCacheDir(),name + ".key");
    RandomAccessFile keyFile=new RandomAccessFile(keyPath,"r");
    keyFile.read(key);
    keyFile.read(iv);
    keyFile.close();
    file=new RandomAccessFile(path,"r");
    file.seek(dataSpec.position);
    fileOffset=(int)dataSpec.position;
    bytesRemaining=dataSpec.length == C.LENGTH_UNSET ? file.length() - dataSpec.position : dataSpec.length;
    if (bytesRemaining < 0) {
      throw new EOFException();
    }
  }
 catch (  IOException e) {
    throw new EncryptedFileDataSourceException(e);
  }
  opened=true;
  transferStarted(dataSpec);
  return bytesRemaining;
}
