/** 
 * ???????SqlSessionFactory
 */
@Bean public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
  SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
  bean.setDataSource(dynamicDataSource);
  bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(resources));
  return bean.getObject();
}
