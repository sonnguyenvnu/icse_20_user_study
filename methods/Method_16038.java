@Override protected BloomFilter createBloomFilter(String name){
  RBloomFilter<String> filter=redissonClient.getBloomFilter("hsweb:bloom-filter:" + name,StringCodec.INSTANCE);
  filter.tryInit(55000000L,0.01);
  return new BloomFilter(){
    @Override public boolean put(    String unique){
      return filter.add(unique);
    }
    @Override public boolean contains(    String unique){
      return filter.contains(unique);
    }
  }
;
}
