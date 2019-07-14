public static Format createVideoContainerFormat(@Nullable String id,@Nullable String label,@Nullable String containerMimeType,String sampleMimeType,String codecs,int bitrate,int width,int height,float frameRate,@Nullable List<byte[]> initializationData,@C.SelectionFlags int selectionFlags){
  return new Format(id,label,containerMimeType,sampleMimeType,codecs,bitrate,NO_VALUE,width,height,frameRate,NO_VALUE,NO_VALUE,null,NO_VALUE,null,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,selectionFlags,null,NO_VALUE,OFFSET_SAMPLE_RELATIVE,initializationData,null,null);
}
