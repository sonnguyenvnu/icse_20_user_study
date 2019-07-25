private synchronized DataSource inner() throws SQLException {
  if (cachedInner != null)   return cachedInner;
 else {
    DataSource out=dereference();
    if (this.isCaching())     cachedInner=out;
    return out;
  }
}
