@Override public void launcher(SpringApplicationBuilder builder,String appName,String profile){
  Properties props=System.getProperties();
  props.setProperty("spring.cloud.nacos.discovery.server-addr",CommonConstant.nacosAddr(profile));
  props.setProperty("spring.cloud.nacos.config.server-addr",CommonConstant.nacosAddr(profile));
  props.setProperty("spring.cloud.sentinel.transport.dashboard",CommonConstant.sentinelAddr(profile));
}
