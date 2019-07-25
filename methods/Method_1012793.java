public static boolean directmime(String mime){
  if (mime != null && (mime.equals(HTTPResource.MP4_TYPEMIME) || mime.equals(HTTPResource.WEBM_TYPEMIME) || mime.equals(HTTPResource.OGG_TYPEMIME) || mime.equals(HTTPResource.AUDIO_OGA_TYPEMIME) || mime.equals(HTTPResource.AUDIO_MP3_TYPEMIME) || mime.equals(HTTPResource.PNG_TYPEMIME) || mime.equals(HTTPResource.JPEG_TYPEMIME) || mime.equals(HTTPResource.GIF_TYPEMIME))) {
    return true;
  }
  return false;
}
