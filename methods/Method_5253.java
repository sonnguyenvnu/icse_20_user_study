protected int getContentType(Format format){
  String sampleMimeType=format.sampleMimeType;
  if (TextUtils.isEmpty(sampleMimeType)) {
    return C.TRACK_TYPE_UNKNOWN;
  }
 else   if (MimeTypes.isVideo(sampleMimeType)) {
    return C.TRACK_TYPE_VIDEO;
  }
 else   if (MimeTypes.isAudio(sampleMimeType)) {
    return C.TRACK_TYPE_AUDIO;
  }
 else   if (mimeTypeIsRawText(sampleMimeType)) {
    return C.TRACK_TYPE_TEXT;
  }
  return C.TRACK_TYPE_UNKNOWN;
}
