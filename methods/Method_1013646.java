public static ThreadFactory create(String cluster,String shard,String namePrefix,boolean daemon){
  return new ClusterShardAwareThreadFactory(cluster,shard,namePrefix,daemon);
}
