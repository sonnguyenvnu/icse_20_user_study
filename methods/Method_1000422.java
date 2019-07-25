protected Object process(ResultSet rs,Entity<?> entity,SqlContext context) throws SQLException {
  if (null != rs && rs.next())   return entity.getObject(rs,context.getFieldMatcher(),prefix);
  return null;
}
