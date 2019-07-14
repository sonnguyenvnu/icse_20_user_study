public static Format createTextContainerFormat(@Nullable String id,@Nullable String label,@Nullable String containerMimeType,@Nullable String sampleMimeType,@Nullable String codecs,int bitrate,@C.SelectionFlags int selectionFlags,@Nullable String language,int accessibilityChannel){
  return new Format(id,label,containerMimeType,sampleMimeType,codecs,bitrate,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,null,NO_VALUE,null,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,selectionFlags,language,accessibilityChannel,OFFSET_SAMPLE_RELATIVE,null,null,null);
}
