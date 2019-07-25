@Override public void handle(ScoreboardObjective objective) throws Exception {
  Scoreboard serverScoreboard=con.getServerSentScoreboard();
switch (objective.getAction()) {
case 0:
    serverScoreboard.addObjective(new Objective(objective.getName(),objective.getValue(),objective.getType().toString()));
  break;
case 1:
serverScoreboard.removeObjective(objective.getName());
break;
case 2:
Objective oldObjective=serverScoreboard.getObjective(objective.getName());
if (oldObjective != null) {
oldObjective.setValue(objective.getValue());
oldObjective.setType(objective.getType().toString());
}
break;
default :
throw new IllegalArgumentException("Unknown objective action: " + objective.getAction());
}
}
