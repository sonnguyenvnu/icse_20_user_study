private MediaSource createMediaSourceFromMediaDescription(MediaMetadataCompat mediaMetadata){
  Uri uri=Uri.parse(mediaMetadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI));
  return mMediaSourceFactory.create(uri);
}
