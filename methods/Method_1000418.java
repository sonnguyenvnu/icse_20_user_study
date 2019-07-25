public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException {
  Entity<?> en=sql.getEntity();
  if (null == en)   throw Lang.makeThrow("SQL without entity : %s",sql.toString());
  FieldMatcher fmh=sql.getContext().getFieldMatcher();
  if (null == fmh)   sql.getContext().setFieldMatcher(FieldFilter.get(en.getType()));
  return process(rs,en,sql.getContext());
}
