@Override public Jedis getResource(){
  while (true) {
    Jedis jedis=super.getResource();
    jedis.setDataSource(this);
    final HostAndPort master=currentHostMaster;
    final HostAndPort connection=new HostAndPort(jedis.getClient().getHost(),jedis.getClient().getPort());
    if (master.equals(connection)) {
      return jedis;
    }
 else {
      returnBrokenResource(jedis);
    }
  }
}
