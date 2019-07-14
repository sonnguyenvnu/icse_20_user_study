private static Intent makeIntent(Music music,Context context){
  return new Intent(context,PlayMusicService.class).putExtra(EXTRA_MUSIC,music);
}
