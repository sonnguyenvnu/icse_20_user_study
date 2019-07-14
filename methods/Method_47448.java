@Bean(name="sqlSessionFactory") public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
  SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
  sessionFactory.setDataSource(dataSource);
  sessionFactory.setPlugins(new Interceptor[]{new PageInterceptor()});
  sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));
  return sessionFactory;
}
