private AspectLog fill(ResultSet resultSet) throws SQLException {
  AspectLog txLog=new AspectLog();
  txLog.setBytes(resultSet.getBytes("BYTES"));
  txLog.setGroupId(resultSet.getString("GROUP_ID"));
  txLog.setMethodStr(resultSet.getString("METHOD_STR"));
  txLog.setTime(resultSet.getLong("TIME"));
  txLog.setUnitId(resultSet.getString("UNIT_ID"));
  txLog.setGroupIdHash(resultSet.getLong("GROUP_ID_HASH"));
  txLog.setUnitIdHash(resultSet.getLong("UNIT_ID_HASH"));
  txLog.setId(resultSet.getLong("ID"));
  return txLog;
}
