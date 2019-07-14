public static boolean isVideoMimeType(String mime){
  return "video/mp4".equals(mime) || SharedConfig.streamMkv && "video/x-matroska".equals(mime);
}
