@ConditionalOnMissingBean(DynamicDataSourceConfigRepository.class) @Bean public DynamicDataSourceConfigRepository inMemoryAtomikosDataSourceRepository(){
  return new InMemoryAtomikosDataSourceRepository();
}
