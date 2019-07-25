private static libvlc_media_t retain(libvlc_media_t mediaInstance){
  libvlc_media_retain(mediaInstance);
  return mediaInstance;
}
