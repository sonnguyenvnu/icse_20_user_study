/** 
 * @see org.sqlite.core.DB#libversion()
 */
@Override public synchronized String libversion(){
  return utf8ByteArrayToString(libversion_utf8());
}
