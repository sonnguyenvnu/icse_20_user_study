@Override protected void doCreate() throws SQLException {
  jdbcTemplate.execute("CREATE USER " + database.quote(name) + " IDENTIFIED BY " + database.quote("FFllyywwaayy00!!"));
  jdbcTemplate.execute("GRANT RESOURCE TO " + database.quote(name));
  jdbcTemplate.execute("GRANT UNLIMITED TABLESPACE TO " + database.quote(name));
}
