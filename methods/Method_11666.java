protected BloomFilter<CharSequence> rebuildBloomFilter(){
  counter=new AtomicInteger(0);
  return BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),expectedInsertions,fpp);
}
