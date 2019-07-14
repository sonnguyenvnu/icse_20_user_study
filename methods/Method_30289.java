public static Intent makeIntent(Game game,Context context){
  return makeIntent((SimpleGame)game,context).putExtra(EXTRA_GAME,game);
}
