@Bean("dynamicDataSource") public DynamicDataSource dynamicDataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,@Qualifier("userDataSource") DataSource miaoMoreDataSource){
  Map<Object,Object> targetDataSources=new HashMap<>();
  targetDataSources.put(DatabaseTypeEnum.PRIMARY,primaryDataSource);
  targetDataSources.put(DatabaseTypeEnum.USER,miaoMoreDataSource);
  DynamicDataSource dataSource=new DynamicDataSource();
  dataSource.setTargetDataSources(targetDataSources);
  dataSource.setDefaultTargetDataSource(primaryDataSource);
  return dataSource;
}
