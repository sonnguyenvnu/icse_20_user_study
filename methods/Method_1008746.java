/** 
 * Called by <tt>xFunc</tt> to return a value.
 * @param value
 */
protected synchronized final void result(byte[] value) throws SQLException {
  checkContext();
  db.result_blob(context,value);
}
