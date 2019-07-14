@Override public void open(DataSpec dataSpec) throws CacheDataSinkException {
  if (dataSpec.length == C.LENGTH_UNSET && dataSpec.isFlagSet(DataSpec.FLAG_DONT_CACHE_IF_LENGTH_UNKNOWN)) {
    this.dataSpec=null;
    return;
  }
  this.dataSpec=dataSpec;
  this.dataSpecFragmentSize=!respectCacheFragmentationFlag || dataSpec.isFlagSet(DataSpec.FLAG_ALLOW_CACHE_FRAGMENTATION) ? fragmentSize : Long.MAX_VALUE;
  dataSpecBytesWritten=0;
  try {
    openNextOutputStream();
  }
 catch (  IOException e) {
    throw new CacheDataSinkException(e);
  }
}
