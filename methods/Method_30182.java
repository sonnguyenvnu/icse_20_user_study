private static GameFragmentResource newInstance(long gameId,SimpleGame simpleGame,Game game){
  return new GameFragmentResource().setArguments(gameId,simpleGame,game);
}
