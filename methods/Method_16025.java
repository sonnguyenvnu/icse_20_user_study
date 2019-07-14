@Bean @Role(BeanDefinition.ROLE_INFRASTRUCTURE) public CacheOperationSource cacheOperationSource(){
  return new FixUseSupperClassCacheOperationSource(new FixUseSupperClassAnnotationParser());
}
