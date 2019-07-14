private boolean tryLock() throws SQLException {
  List<Boolean> results=jdbcTemplate.query("SELECT pg_try_advisory_lock(" + lockNum + ")",new RowMapper<Boolean>(){
    @Override public Boolean mapRow(    ResultSet rs) throws SQLException {
      return "t".equals(rs.getString("pg_try_advisory_lock"));
    }
  }
);
  return results.size() == 1 && results.get(0);
}
