@Bean public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSourceProxy);
  jdbcTemplate.update("delete from storage_tbl where commodity_code = 'C00321'");
  jdbcTemplate.update("insert into storage_tbl(commodity_code, count) values ('C00321', 100)");
  return jdbcTemplate;
}
