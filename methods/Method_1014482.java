@Override public void handle(GameEvent event){
  DeathEvent DeathEvent=(DeathEvent)event;
  GameObject object=DeathEvent.getSource();
  if (object instanceof ControllableUnit) {
    ((ControllableUnit)object).getParent().getStats().incrementStat("death",1);
  }
}
