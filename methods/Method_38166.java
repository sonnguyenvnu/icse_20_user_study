protected void assertTxIsClosed(){
  assertSessionIsOpen();
  if (txActive) {
    throw new DbSqlException("TX already started for this session");
  }
}
