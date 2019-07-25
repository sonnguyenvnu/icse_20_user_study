@Override public void handle(ScoreboardScore score) throws Exception {
  Scoreboard serverScoreboard=con.getServerSentScoreboard();
switch (score.getAction()) {
case 0:
    Score s=new Score(score.getItemName(),score.getScoreName(),score.getValue());
  serverScoreboard.removeScore(score.getItemName());
serverScoreboard.addScore(s);
break;
case 1:
serverScoreboard.removeScore(score.getItemName());
break;
default :
throw new IllegalArgumentException("Unknown scoreboard action: " + score.getAction());
}
}
