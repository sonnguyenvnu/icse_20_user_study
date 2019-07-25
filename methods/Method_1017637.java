private void initialize() throws SQLException {
  if (initialized) {
    throw new PSQLException(GT.tr("This SQLXML object has already been initialized, so you cannot manipulate it further."),PSQLState.OBJECT_NOT_IN_STATE);
  }
  initialized=true;
}
