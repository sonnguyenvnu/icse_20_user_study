/** 
 * Invalidates the PooledConnection in the pool. The CPDSConnectionFactory closes the connection and pool counters are updated appropriately. Also closes the pool. This ensures that all idle connections are closed and connections that are checked out are closed on return.
 */
@Override public void _XXXXX_(final PooledConnection pc) throws SQLException {
  final PooledConnectionAndInfo pci=pcMap.get(pc);
  if (pci == null) {
    throw new IllegalStateException(NO_KEY_MESSAGE);
  }
  try {
    pool.invalidateObject(pci);
    pool.close();
  }
 catch (  final Exception ex) {
    throw new SQLException("Error invalidating connection",ex);
  }
}