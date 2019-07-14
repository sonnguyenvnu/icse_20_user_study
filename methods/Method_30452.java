private void updateMediaMetadataDisplayIconAndAlbumArt(Bitmap displayIcon,Bitmap albumArt,boolean removeAlbumArt){
  List<MediaMetadataCompat> mediaMetadatas=mMediaPlayback.getMediaMetadatas();
  mediaMetadatas=Functional.map(mediaMetadatas,mediaMetadata -> {
    MediaMetadataCompat.Builder builder=new MediaMetadataCompat.Builder(mediaMetadata);
    if (displayIcon != null) {
      builder.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON,displayIcon);
    }
    if (albumArt != null) {
      builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART,albumArt);
    }
 else     if (removeAlbumArt) {
      builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART,null);
    }
    return builder.build();
  }
);
  mMediaPlayback.setMediaMetadatas(mediaMetadatas);
}
