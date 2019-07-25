@Override public void update(Object args,Observable observable){
  MigrationEvent event=(MigrationEvent)args;
  if (event.isDone()) {
    logger.info("[update][done]{}",event);
  }
}
