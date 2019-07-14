@Bean(name="mysqlSqlSessionFactory") @Primary public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysqldatasource") DataSource dataSource) throws Exception {
  final SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
  sessionFactory.setDataSource(dataSource);
  sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MysqlDatasourceConfig.MAPPER_LOCATION));
  return sessionFactory.getObject();
}
