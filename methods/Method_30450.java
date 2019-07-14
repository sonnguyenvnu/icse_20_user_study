private void loadMediaMetadataDisplayIconAndAlbumArt(Music music){
  String albumArtUrl=music.cover.getLargeUrl();
  GlideApp.with(this).asBitmap().dontTransform().downsample(DownsampleStrategy.CENTER_INSIDE).override(mMediaDisplayIconMaxSize).load(albumArtUrl).into(new SimpleTarget<Bitmap>(){
    @Override public void onResourceReady(    Bitmap displayIcon,    Transition<? super Bitmap> transition){
      if (music.id != getMusicId()) {
        return;
      }
      updateMediaMetadataDisplayIconAndAlbumArt(displayIcon,null,false);
      if (Settings.SHOW_ALBUM_ART_ON_LOCK_SCREEN.getValue()) {
        loadMediaMetadataAlbumArt(music);
      }
    }
  }
);
}
