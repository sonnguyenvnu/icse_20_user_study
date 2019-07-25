/** 
 * For Blobs this should be in bytes while for Clobs it should be in characters. Since we really haven't figured out how to handle character sets for Clobs the current implementation uses bytes for both Blobs and Clobs.
 * @param len maximum length
 * @throws SQLException if operation fails
 */
public synchronized void truncate(long len) throws SQLException {
  checkFreed();
  if (!conn.haveMinimumServerVersion(ServerVersion.v8_3)) {
    throw new PSQLException(GT.tr("Truncation of large objects is only implemented in 8.3 and later servers."),PSQLState.NOT_IMPLEMENTED);
  }
  if (len < 0) {
    throw new PSQLException(GT.tr("Cannot truncate LOB to a negative length."),PSQLState.INVALID_PARAMETER_VALUE);
  }
  if (len > Integer.MAX_VALUE) {
    if (support64bit) {
      getLo(true).truncate64(len);
    }
 else {
      throw new PSQLException(GT.tr("PostgreSQL LOBs can only index to: {0}",Integer.MAX_VALUE),PSQLState.INVALID_PARAMETER_VALUE);
    }
  }
 else {
    getLo(true).truncate((int)len);
  }
}
