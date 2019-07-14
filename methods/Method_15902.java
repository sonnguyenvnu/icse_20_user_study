@Override public void setNonNullParameter(PreparedStatement ps,int i,Map<String,Object> parameter,JdbcType jdbcType) throws SQLException {
  ps.setString(i,"{}");
}
