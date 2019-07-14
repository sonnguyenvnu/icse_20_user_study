@Override public void setParameter(PreparedStatement ps,int i,Map<String,Object> parameter,JdbcType jdbcType) throws SQLException {
  ps.setString(i,JSON.toJSONString(parameter,SerializerFeature.WriteClassName));
}
