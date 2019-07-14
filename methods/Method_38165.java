protected void assertSessionIsOpen(){
  if (queries == null) {
    throw new DbSqlException("Session is closed");
  }
}
