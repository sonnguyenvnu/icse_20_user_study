@Override public Long msetnx(final String... keysvalues){
  String[] keys=new String[keysvalues.length / 2];
  for (int keyIdx=0; keyIdx < keys.length; keyIdx++) {
    keys[keyIdx]=keysvalues[keyIdx * 2];
  }
  return new JedisClusterCommand<Long>(connectionHandler,maxRedirections){
    @Override public Long execute(    Jedis connection){
      return connection.msetnx(keysvalues);
    }
  }
.run(keys.length,keys);
}
