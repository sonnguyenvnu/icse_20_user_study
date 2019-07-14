public void overrideFromEnv(Environment env){
  if (StringUtils.isEmpty(this.getServerAddr())) {
    this.setServerAddr(env.resolvePlaceholders("${spring.cloud.nacos.discovery.server-addr:}"));
  }
  if (StringUtils.isEmpty(this.getNamespace())) {
    this.setNamespace(env.resolvePlaceholders("${spring.cloud.nacos.discovery.namespace:}"));
  }
  if (StringUtils.isEmpty(this.getAccessKey())) {
    this.setAccessKey(env.resolvePlaceholders("${spring.cloud.nacos.discovery.access-key:}"));
  }
  if (StringUtils.isEmpty(this.getSecretKey())) {
    this.setSecretKey(env.resolvePlaceholders("${spring.cloud.nacos.discovery.secret-key:}"));
  }
  if (StringUtils.isEmpty(this.getLogName())) {
    this.setLogName(env.resolvePlaceholders("${spring.cloud.nacos.discovery.log-name:}"));
  }
  if (StringUtils.isEmpty(this.getClusterName())) {
    this.setClusterName(env.resolvePlaceholders("${spring.cloud.nacos.discovery.cluster-name:}"));
  }
  if (StringUtils.isEmpty(this.getEndpoint())) {
    this.setEndpoint(env.resolvePlaceholders("${spring.cloud.nacos.discovery.endpoint:}"));
  }
}
