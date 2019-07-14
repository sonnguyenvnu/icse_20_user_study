@Override public void preCheck(String dataSourceName){
  if (StringUtils.isEmpty(serverAddr)) {
    serverAddr=this.getEnv().getProperty("spring.cloud.sentinel.datasource.nacos.server-addr","");
    if (StringUtils.isEmpty(serverAddr)) {
      throw new IllegalArgumentException("NacosDataSource server-addr is empty");
    }
  }
}
