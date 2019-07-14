@Override public Strategy createStrategyIfMatch(Class target,Method method){
  if (ignoreMethod.contains(method.getName())) {
    return null;
  }
  UseDataSource useDataSource=AopUtils.findAnnotation(target,method,UseDataSource.class);
  UseDefaultDataSource useDefaultDataSource=AopUtils.findAnnotation(target,method,UseDefaultDataSource.class);
  boolean support=useDataSource != null || useDefaultDataSource != null;
  if (support) {
    return new Strategy(){
      @Override public boolean isUseDefaultDataSource(){
        return useDefaultDataSource != null;
      }
      @Override public boolean isFallbackDefault(){
        return useDataSource != null && useDataSource.fallbackDefault();
      }
      @Override public String getDataSourceId(){
        return useDataSource == null ? null : useDataSource.value();
      }
      @Override public String toString(){
        return "Annotation Strategy(" + (useDataSource != null ? useDataSource : useDefaultDataSource) + ")";
      }
      @Override public String getDatabase(){
        return useDataSource == null ? null : StringUtils.isEmpty(useDataSource.database()) ? null : useDataSource.database();
      }
    }
;
  }
  return null;
}
