private static String adjustRequestMimeType(UUID uuid,String mimeType){
  if (Util.SDK_INT < 26 && C.CLEARKEY_UUID.equals(uuid) && (MimeTypes.VIDEO_MP4.equals(mimeType) || MimeTypes.AUDIO_MP4.equals(mimeType))) {
    return CENC_SCHEME_MIME_TYPE;
  }
  return mimeType;
}
