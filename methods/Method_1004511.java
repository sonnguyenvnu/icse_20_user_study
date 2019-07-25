protected JdbcTemplate create(DatasourceWrapper dataSource){
  return new JdbcTemplate(dataSource.datasource());
}
