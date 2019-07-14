private void loadMediaMetadataAlbumArt(Music music){
  String albumArtUrl=music.cover.getLargeUrl();
  GlideApp.with(PlayMusicService.this).asBitmap().dontTransform().downsample(DownsampleStrategy.CENTER_INSIDE).override(mMediaArtMaxSize).load(albumArtUrl).into(new SimpleTarget<Bitmap>(){
    @Override public void onResourceReady(    Bitmap albumArt,    Transition<? super Bitmap> transition){
      if (music.id != getMusicId()) {
        return;
      }
      if (!Settings.SHOW_ALBUM_ART_ON_LOCK_SCREEN.getValue()) {
        return;
      }
      updateMediaMetadataDisplayIconAndAlbumArt(null,albumArt,false);
    }
  }
);
}
