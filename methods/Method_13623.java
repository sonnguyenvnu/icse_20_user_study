@Override public void preCheck(String dataSourceName){
  if (StringUtils.isEmpty(serverAddr)) {
    serverAddr=this.getEnv().getProperty("spring.cloud.sentinel.datasource.zk.server-addr","");
    if (StringUtils.isEmpty(serverAddr)) {
      throw new IllegalArgumentException("ZookeeperDataSource server-addr is empty");
    }
  }
}
