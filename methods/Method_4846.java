public static Format createContainerFormat(@Nullable String id,@Nullable String label,@Nullable String containerMimeType,@Nullable String sampleMimeType,@Nullable String codecs,int bitrate,@C.SelectionFlags int selectionFlags,@Nullable String language){
  return new Format(id,label,containerMimeType,sampleMimeType,codecs,bitrate,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,null,NO_VALUE,null,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,selectionFlags,language,NO_VALUE,OFFSET_SAMPLE_RELATIVE,null,null,null);
}
