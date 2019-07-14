private boolean mediaMetadataHasAlbumArt(){
  return Functional.some(mMediaPlayback.getMediaMetadatas(),mediaMetadata -> mediaMetadata.getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART) != null);
}
