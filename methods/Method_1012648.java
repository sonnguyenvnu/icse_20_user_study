@Override public boolean load(File f){
  configuration.addProperty(MEDIAPARSERV2,true);
  configuration.addProperty(MEDIAPARSERV2_THUMB,true);
  configuration.addProperty(SUPPORTED,"f:flv v:h264|hls a:aac-lc m:video/flash");
  configuration.addProperty(SUPPORTED,"f:mp4 m:video/mp4");
  configuration.addProperty(SUPPORTED,"f:mp3 n:2 m:audio/mpeg");
  configuration.addProperty(SUPPORTED,"f:ogg v:theora m:video/ogg");
  configuration.addProperty(SUPPORTED,"f:oga a:vorbis|flac m:audio/ogg");
  configuration.addProperty(SUPPORTED,"f:wav n:2 m:audio/wav");
  configuration.addProperty(SUPPORTED,"f:webm v:vp8|vp9 m:video/webm");
  configuration.addProperty(SUPPORTED,"f:bmp m:image/bmp");
  configuration.addProperty(SUPPORTED,"f:jpg m:image/jpeg");
  configuration.addProperty(SUPPORTED,"f:png m:image/png");
  configuration.addProperty(SUPPORTED,"f:gif m:image/gif");
  configuration.addProperty(SUPPORTED,"f:tiff m:image/tiff");
  configuration.addProperty(TRANSCODE_AUDIO,MP3);
  return true;
}
