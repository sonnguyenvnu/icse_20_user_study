@PostConstruct protected void startup(){
  this.name=this.getClass().getSimpleName();
  try {
    objectName=new ObjectName(domain,"type",name);
    mbeanServer=ManagementFactory.getPlatformMBeanServer();
    mbeanServer.registerMBean(this,objectName);
  }
 catch (  Exception e) {
    throw new IllegalStateException("Error during registration of " + name + " into JMX:" + e,e);
  }
}
