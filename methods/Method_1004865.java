public static MysqlSavedSchema restore(MaxwellContext context,Position targetPosition) throws SQLException, InvalidSchemaError {
  return restore(context.getMaxwellConnectionPool(),context.getServerID(),context.getCaseSensitivity(),targetPosition);
}
