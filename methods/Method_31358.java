@Override protected InformixTable[] doAllTables() throws SQLException {
  return findTables("SELECT t.tabname FROM \"informix\".systables AS t" + " WHERE owner=? AND t.tabid > 99 AND t.tabtype='T'" + " AND t.tabname NOT IN (" + " 'calendarpatterns', 'calendartable'," + " 'tscontainertable', 'tscontainerwindowtable', 'tsinstancetable', " + " 'tscontainerusageactivewindowvti', 'tscontainerusagedormantwindowvti'" + ")",name);
}
