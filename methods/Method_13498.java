@Bean public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSourceProxy);
  jdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
  jdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 10000)");
  return jdbcTemplate;
}
