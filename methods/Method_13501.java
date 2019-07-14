@Bean public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSourceProxy);
  jdbcTemplate.execute("TRUNCATE TABLE order_tbl");
  return jdbcTemplate;
}
