@Override public void append(MediaItem mediaItem){
  if (!MediaItemUtils.isDash(mediaItem)) {
    return;
  }
  Set<MediaItem> placeholder=null;
  String mimeType=extractMimeType(mediaItem);
  if (mimeType != null) {
switch (mimeType) {
case MIME_WEBM_AUDIO:
      placeholder=mWEBMAudios;
    break;
case MIME_WEBM_VIDEO:
  placeholder=mWEBMVideos;
break;
case MIME_MP4_AUDIO:
placeholder=mMP4Audios;
break;
case MIME_MP4_VIDEO:
placeholder=mMP4Videos;
break;
}
}
if (placeholder != null) {
placeholder.add(mediaItem);
}
}
