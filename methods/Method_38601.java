@Override public JoyDb withEntityManager(final Consumer<DbEntityManager> dbEntityManagerConsumer){
  requireNotStarted(connectionProvider);
  dbEntityManagerConsumers.add(dbEntityManagerConsumer);
  return this;
}
