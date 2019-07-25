public static boolean supports(DLNAResource dlna){
  if (dlna instanceof VirtualVideoAction) {
    return true;
  }
  DLNAMediaInfo media=dlna.getMedia();
  return media != null && RemoteUtil.directmime(media.getMimeType()) || supportedFormat(dlna.getFormat()) || dlna.getPlayer() instanceof FFMpegVideo;
}
