public static Intent makeIntent(long musicId,Context context){
  return new Intent(context,MusicActivity.class).putExtra(EXTRA_MUSIC_ID,musicId);
}
