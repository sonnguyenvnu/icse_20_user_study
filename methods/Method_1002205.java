@Override public void append(MediaItem mediaItem){
  if (!MediaItemUtils.isDash(mediaItem)) {
    mVideos.add(mediaItem);
  }
}
