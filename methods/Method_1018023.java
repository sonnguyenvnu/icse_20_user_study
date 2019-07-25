@Nullable @Override public Row apply(@Nullable final ResultSet rs){
  try {
    return (rs != null) ? mapRow(rs) : null;
  }
 catch (  final SQLException e) {
    throw Throwables.propagate(e);
  }
}
