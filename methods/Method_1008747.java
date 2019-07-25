/** 
 * Called by <tt>xFunc</tt> to return a value.
 */
protected synchronized final void result() throws SQLException {
  checkContext();
  db.result_null(context);
}
