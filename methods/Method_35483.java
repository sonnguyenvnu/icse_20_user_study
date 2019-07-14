private static String extractMetadata(MediaMetadataRetriever retriever,int key,String defaultValue){
  String value=retriever.extractMetadata(key);
  if (TextUtils.isEmpty(value)) {
    value=defaultValue;
  }
  return value;
}
