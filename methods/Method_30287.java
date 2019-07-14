public static Intent makeIntent(long gameId,Context context){
  return new Intent(context,GameActivity.class).putExtra(EXTRA_GAME_ID,gameId);
}
