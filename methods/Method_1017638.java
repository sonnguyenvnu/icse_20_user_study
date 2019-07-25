public <T>T unwrap(Class<T> iface) throws SQLException {
  if (iface.isAssignableFrom(getClass())) {
    return iface.cast(this);
  }
  throw new SQLException("Cannot unwrap to " + iface.getName());
}
