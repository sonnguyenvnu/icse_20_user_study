/** 
 * Returns either self or delegate (in this order) if one of them can be cast to supplied parameter class. Does *not* support recursive unwrapping of the delegate to retain Java 5 compatibility.
 */
@Override public <T>T unwrap(Class<T> iface) throws SQLException {
  if (iface.isAssignableFrom(SmartDataSource.class)) {
    @SuppressWarnings("unchecked") T casted=(T)this;
    return casted;
  }
 else   if (iface.isAssignableFrom(dataSource.getClass())) {
    @SuppressWarnings("unchecked") T casted=(T)dataSource;
    return casted;
  }
  throw new SQLException("Unsupported class " + iface.getSimpleName());
}
