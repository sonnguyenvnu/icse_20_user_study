public <T>T unwrap(Class<T> iface) throws SQLException {
  if (this.isWrapperForThis(iface))   return (T)this;
 else   throw new SQLException(this + " is not a wrapper for or implementation of " + iface.getName());
}
