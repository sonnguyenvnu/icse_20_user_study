private static MetaHolder.Builder setDefaultProperties(MetaHolder.Builder builder,Class<?> declaringClass,final ProceedingJoinPoint joinPoint){
  Optional<DefaultProperties> defaultPropertiesOpt=AopUtils.getAnnotation(joinPoint,DefaultProperties.class);
  builder.defaultGroupKey(declaringClass.getSimpleName());
  if (defaultPropertiesOpt.isPresent()) {
    DefaultProperties defaultProperties=defaultPropertiesOpt.get();
    builder.defaultProperties(defaultProperties);
    if (StringUtils.isNotBlank(defaultProperties.groupKey())) {
      builder.defaultGroupKey(defaultProperties.groupKey());
    }
    if (StringUtils.isNotBlank(defaultProperties.threadPoolKey())) {
      builder.defaultThreadPoolKey(defaultProperties.threadPoolKey());
    }
  }
  return builder;
}
