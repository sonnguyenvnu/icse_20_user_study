/** 
 * Drops this schema.
 * @param schema The schema to drop.
 * @throws FlywayException when the drop failed.
 */
private void dropSchema(final Schema schema){
  LOG.debug("Dropping schema " + schema + " ...");
  StopWatch stopWatch=new StopWatch();
  stopWatch.start();
  try {
    new TransactionTemplate(connection.getJdbcConnection()).execute(new Callable<Object>(){
      @Override public Void call(){
        schema.drop();
        return null;
      }
    }
);
  }
 catch (  FlywaySqlException e) {
    LOG.debug(e.getMessage());
    LOG.warn("Unable to drop schema " + schema + ". Attempting clean instead...");
    new TransactionTemplate(connection.getJdbcConnection()).execute(new Callable<Object>(){
      @Override public Void call(){
        schema.clean();
        return null;
      }
    }
);
  }
  stopWatch.stop();
  LOG.info(String.format("Successfully dropped schema %s (execution time %s)",schema,TimeFormat.format(stopWatch.getTotalTimeMillis())));
}
