/** 
 * This should be simply passing the byte value of the pattern Blob.
 */
public synchronized long position(Clob pattern,long start) throws SQLException {
  checkFreed();
  throw org.postgresql.Driver.notImplemented(this.getClass(),"position(Clob,start)");
}
