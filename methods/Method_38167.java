protected void assertTxIsActive(){
  assertSessionIsOpen();
  if (!txActive) {
    throw new DbSqlException("TX not available for this session");
  }
}
