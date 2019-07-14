@Override public long open(DataSpec dataSpec) throws ContentDataSourceException {
  try {
    uri=dataSpec.uri;
    transferInitializing(dataSpec);
    assetFileDescriptor=resolver.openAssetFileDescriptor(uri,"r");
    if (assetFileDescriptor == null) {
      throw new FileNotFoundException("Could not open file descriptor for: " + uri);
    }
    inputStream=new FileInputStream(assetFileDescriptor.getFileDescriptor());
    long assetStartOffset=assetFileDescriptor.getStartOffset();
    long skipped=inputStream.skip(assetStartOffset + dataSpec.position) - assetStartOffset;
    if (skipped != dataSpec.position) {
      throw new EOFException();
    }
    if (dataSpec.length != C.LENGTH_UNSET) {
      bytesRemaining=dataSpec.length;
    }
 else {
      long assetFileDescriptorLength=assetFileDescriptor.getLength();
      if (assetFileDescriptorLength == AssetFileDescriptor.UNKNOWN_LENGTH) {
        FileChannel channel=inputStream.getChannel();
        long channelSize=channel.size();
        bytesRemaining=channelSize == 0 ? C.LENGTH_UNSET : channelSize - channel.position();
      }
 else {
        bytesRemaining=assetFileDescriptorLength - skipped;
      }
    }
  }
 catch (  IOException e) {
    throw new ContentDataSourceException(e);
  }
  opened=true;
  transferStarted(dataSpec);
  return bytesRemaining;
}
