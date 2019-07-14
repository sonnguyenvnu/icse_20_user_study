public void setMediaMetadatas(List<MediaMetadataCompat> mediaMetadatas){
  mMediaMetadatas.clear();
  mMediaMetadatas.addAll(mediaMetadatas);
  mNeedWifiLock=Functional.some(mMediaMetadatas,mediaMetadata -> {
    String uri=mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI);
    return !TextUtils.isEmpty(uri) && UriUtils.isWebScheme(Uri.parse(uri));
  }
);
  if (mPlayer.getPlaybackState() != Player.STATE_IDLE) {
    updateMediaSessionMetadata();
  }
}
