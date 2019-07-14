private static GameResource newInstance(long gameId,SimpleGame simpleGame,Game game){
  GameResource instance=new GameResource();
  instance.setArguments(gameId,simpleGame,game);
  return instance;
}
