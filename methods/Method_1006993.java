@Override public void write(List<? extends Player> players) throws Exception {
  for (  Player player : players) {
    playerDao.savePlayer(player);
  }
}
