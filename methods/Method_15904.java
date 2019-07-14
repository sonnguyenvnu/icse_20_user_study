@Override public void setNonNullParameter(PreparedStatement ps,int i,Set parameter,JdbcType jdbcType) throws SQLException {
  ps.setString(i,"[]");
}
