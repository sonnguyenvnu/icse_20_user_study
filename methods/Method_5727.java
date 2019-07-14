@Override public DataSink createDataSink(){
  CacheDataSink dataSink=new CacheDataSink(cache,fragmentSize,bufferSize);
  dataSink.experimental_setSyncFileDescriptor(syncFileDescriptor);
  dataSink.experimental_setRespectCacheFragmentationFlag(respectCacheFragmentationFlag);
  return dataSink;
}
