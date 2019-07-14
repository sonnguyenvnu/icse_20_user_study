private void loadAlbum(Intent intent){
  album=intent.getParcelableExtra(EXTRA_ARGS_ALBUM);
  position=intent.getIntExtra(EXTRA_ARGS_POSITION,0);
  media=intent.getParcelableArrayListExtra(EXTRA_ARGS_MEDIA);
}
