public ShardedJedisPipeline pipelined(){
  ShardedJedisPipeline pipeline=new ShardedJedisPipeline();
  pipeline.setShardedJedis(this);
  return pipeline;
}
