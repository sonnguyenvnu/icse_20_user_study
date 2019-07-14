@Bean(name="settThreadPoolExecutor",initMethod="init",destroyMethod="destroy") public SettThreadPoolExecutor settThreadPoolExecutor(){
  SettThreadPoolExecutor settThreadPoolExecutor=new SettThreadPoolExecutor();
  settThreadPoolExecutor.setCorePoolSize(5);
  settThreadPoolExecutor.setMaxPoolSize(10);
  settThreadPoolExecutor.setWorkQueueSize(256);
  settThreadPoolExecutor.setKeepAliveTime(3);
  return settThreadPoolExecutor;
}
