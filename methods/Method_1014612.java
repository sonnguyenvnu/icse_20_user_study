@Override public void handle(GameEvent event){
  currentDayTime=(int)((TickEvent)event).getTime() / HOUR_LENGTH % 24;
  sunIntensity=Math.max((int)Math.round((-(0.25 * currentDayTime * currentDayTime) + (6 * currentDayTime) - 27)),0) + 1;
}
