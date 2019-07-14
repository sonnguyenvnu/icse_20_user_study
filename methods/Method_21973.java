/** 
 * ??????????.
 * @param jobShardingStrategyClassName ????????
 * @return ????????
 */
public static JobShardingStrategy getStrategy(final String jobShardingStrategyClassName){
  if (Strings.isNullOrEmpty(jobShardingStrategyClassName)) {
    return new AverageAllocationJobShardingStrategy();
  }
  try {
    Class<?> jobShardingStrategyClass=Class.forName(jobShardingStrategyClassName);
    if (!JobShardingStrategy.class.isAssignableFrom(jobShardingStrategyClass)) {
      throw new JobConfigurationException("Class '%s' is not job strategy class",jobShardingStrategyClassName);
    }
    return (JobShardingStrategy)jobShardingStrategyClass.newInstance();
  }
 catch (  final ClassNotFoundException|InstantiationException|IllegalAccessException ex) {
    throw new JobConfigurationException("Sharding strategy class '%s' config error, message details are '%s'",jobShardingStrategyClassName,ex.getMessage());
  }
}
