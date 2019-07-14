private Map<Integer,String> toMap(final String originalShardingItemParameters){
  if (Strings.isNullOrEmpty(originalShardingItemParameters)) {
    return Collections.emptyMap();
  }
  String[] shardingItemParameters=originalShardingItemParameters.split(PARAMETER_DELIMITER);
  Map<Integer,String> result=new HashMap<>(shardingItemParameters.length);
  for (  String each : shardingItemParameters) {
    ShardingItem shardingItem=parse(each,originalShardingItemParameters);
    result.put(shardingItem.item,shardingItem.parameter);
  }
  return result;
}
