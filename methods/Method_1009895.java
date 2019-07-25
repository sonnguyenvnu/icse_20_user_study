private void reset(boolean txn_known_resolved) throws SQLException {
  C3P0ImplUtils.resetTxnState(physicalConnection,forceIgnoreUnresolvedTransactions,autoCommitOnClose,txn_known_resolved);
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
  if (readOnly_nondefault) {
    physicalConnection.setReadOnly(dflt_readOnly);
    readOnly_nondefault=false;
  }
  if (typeMap_nondefault) {
    physicalConnection.setTypeMap(dflt_typeMap);
    typeMap_nondefault=false;
  }
}
