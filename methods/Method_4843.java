public static Format createTextContainerFormat(@Nullable String id,@Nullable String label,@Nullable String containerMimeType,@Nullable String sampleMimeType,@Nullable String codecs,int bitrate,@C.SelectionFlags int selectionFlags,@Nullable String language){
  return createTextContainerFormat(id,label,containerMimeType,sampleMimeType,codecs,bitrate,selectionFlags,language,NO_VALUE);
}
