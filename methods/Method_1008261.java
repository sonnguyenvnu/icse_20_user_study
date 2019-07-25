public List<ShardRouting> shards(Predicate<ShardRouting> predicate){
  List<ShardRouting> shards=new ArrayList<>();
  for (  RoutingNode routingNode : this) {
    for (    ShardRouting shardRouting : routingNode) {
      if (predicate.test(shardRouting)) {
        shards.add(shardRouting);
      }
    }
  }
  return shards;
}
