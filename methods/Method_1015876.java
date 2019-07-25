@Override public void handle(ScoreboardDisplay displayScoreboard) throws Exception {
  Scoreboard serverScoreboard=con.getServerSentScoreboard();
  serverScoreboard.setName(displayScoreboard.getName());
  serverScoreboard.setPosition(Position.values()[displayScoreboard.getPosition()]);
}
