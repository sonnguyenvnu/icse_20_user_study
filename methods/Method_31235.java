private void restoreOriginalSchema(){
  new TransactionTemplate(jdbcConnection).execute(new Callable<Void>(){
    @Override public Void call(){
      try {
        doChangeCurrentSchemaOrSearchPathTo(originalSchemaNameOrSearchPath);
      }
 catch (      SQLException e) {
        throw new FlywaySqlException("Unable to restore original schema",e);
      }
      return null;
    }
  }
);
}
