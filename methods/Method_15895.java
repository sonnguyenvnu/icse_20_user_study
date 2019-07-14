@Override public void setNonNullParameter(PreparedStatement ps,int i,List<Object> parameter,JdbcType jdbcType) throws SQLException {
  ps.setString(i,"[]");
}
