@Bean @ConditionalOnMissingBean(DataAccessConfigBuilderFactory.class) @ConfigurationProperties(prefix="hsweb.authorization.data-access",ignoreInvalidFields=true) public SimpleDataAccessConfigBuilderFactory dataAccessConfigBuilderFactory(){
  SimpleDataAccessConfigBuilderFactory factory=new SimpleDataAccessConfigBuilderFactory();
  if (null != dataAccessConfigConverts) {
    dataAccessConfigConverts.forEach(factory::addConvert);
  }
  return factory;
}
