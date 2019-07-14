public static Intent makeIntent(SimpleGame simpleGame,Context context){
  return makeIntent(simpleGame.id,context).putExtra(EXTRA_SIMPLE_GAME,simpleGame);
}
