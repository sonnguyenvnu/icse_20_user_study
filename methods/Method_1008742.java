/** 
 * @see org.sqlite.core.DB#errmsg()
 */
@Override synchronized String errmsg(){
  return utf8ByteArrayToString(errmsg_utf8());
}
