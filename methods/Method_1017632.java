/** 
 * For now, this is not implemented.
 */
public synchronized long position(String pattern,long start) throws SQLException {
  checkFreed();
  throw org.postgresql.Driver.notImplemented(this.getClass(),"position(String,long)");
}
