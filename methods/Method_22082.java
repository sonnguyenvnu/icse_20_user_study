private List<Integer> getAllItems(){
  int shardingTotalCount=configService.load(true).getTypeConfig().getCoreConfig().getShardingTotalCount();
  List<Integer> result=new ArrayList<>(shardingTotalCount);
  for (int i=0; i < shardingTotalCount; i++) {
    result.add(i);
  }
  return result;
}
