private void reset(boolean known_resolved_txn) throws SQLException {
  ensureOkay();
  C3P0ImplUtils.resetTxnState(physicalConnection,forceIgnoreUnresolvedTransactions,autoCommitOnClose,known_resolved_txn);
  if (isolation_lvl_nondefault) {
    physicalConnection.setTransactionIsolation(dflt_txn_isolation);
    isolation_lvl_nondefault=false;
  }
  if (catalog_nondefault) {
    physicalConnection.setCatalog(dflt_catalog);
    catalog_nondefault=false;
  }
  if (holdability_nondefault) {
    physicalConnection.setHoldability(dflt_holdability);
    holdability_nondefault=false;
  }
  try {
    physicalConnection.setReadOnly(false);
  }
 catch (  Throwable t) {
    if (logger.isLoggable(MLevel.FINE))     logger.log(MLevel.FINE,"A Throwable occurred while trying to reset the readOnly property of our Connection to false!",t);
  }
  try {
    if (supports_setTypeMap)     physicalConnection.setTypeMap(Collections.EMPTY_MAP);
  }
 catch (  Throwable t) {
    if (logger.isLoggable(MLevel.FINE))     logger.log(MLevel.FINE,"A Throwable occurred while trying to reset the typeMap property of our Connection to Collections.EMPTY_MAP!",t);
  }
}
