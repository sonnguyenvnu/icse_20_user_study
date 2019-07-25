private synchronized Exception reinitialize(){
  try {
    if (!(pds instanceof AbstractComboPooledDataSource) && pds instanceof AbstractPoolBackedDataSource) {
      if (this.cpds instanceof WrapperConnectionPoolDataSource)       ((WrapperConnectionPoolDataSource)this.cpds).removePropertyChangeListener(pcl);
      this.cpds=null;
      this.unpooledDataSource=null;
      this.cpds=((AbstractPoolBackedDataSource)pds).getConnectionPoolDataSource();
      if (cpds instanceof WrapperConnectionPoolDataSource) {
        this.unpooledDataSource=((WrapperConnectionPoolDataSource)cpds).getNestedDataSource();
        ((WrapperConnectionPoolDataSource)this.cpds).addPropertyChangeListener(pcl);
      }
    }
    pdsAttrInfos=extractAttributeInfos(pds);
    cpdsAttrInfos=extractAttributeInfos(cpds);
    unpooledDataSourceAttrInfos=extractAttributeInfos(unpooledDataSource);
    Set allAttrNames=new HashSet();
    allAttrNames.addAll(pdsAttrInfos.keySet());
    allAttrNames.addAll(cpdsAttrInfos.keySet());
    allAttrNames.addAll(unpooledDataSourceAttrInfos.keySet());
    Set allAttrs=new HashSet();
    for (Iterator ii=allAttrNames.iterator(); ii.hasNext(); ) {
      String name=(String)ii.next();
      Object attrInfo;
      attrInfo=pdsAttrInfos.get(name);
      if (attrInfo == null)       attrInfo=cpdsAttrInfos.get(name);
      if (attrInfo == null)       attrInfo=unpooledDataSourceAttrInfos.get(name);
      allAttrs.add(attrInfo);
    }
    String className=this.getClass().getName();
    MBeanAttributeInfo[] attrInfos=(MBeanAttributeInfo[])allAttrs.toArray(new MBeanAttributeInfo[allAttrs.size()]);
    Class[] ctorArgClasses={PooledDataSource.class,String.class,MBeanServer.class};
    MBeanConstructorInfo[] constrInfos=new MBeanConstructorInfo[]{new MBeanConstructorInfo("Constructor from PooledDataSource",this.getClass().getConstructor(ctorArgClasses))};
    this.info=new MBeanInfo(this.getClass().getName(),"An MBean to monitor and manage a PooledDataSource",attrInfos,constrInfos,OP_INFS,null);
    try {
      ObjectName oname=ObjectName.getInstance(mbeanName);
      if (mbs.isRegistered(oname)) {
        mbs.unregisterMBean(oname);
        if (logger.isLoggable(MLevel.FINE))         logger.log(MLevel.FINE,"MBean: " + oname.toString() + " unregistered, in order to be reregistered after update.");
      }
      this.mbeanName=ActiveManagementCoordinator.getPdsObjectNameStr(pds);
      oname=ObjectName.getInstance(mbeanName);
      mbs.registerMBean(this,oname);
      if (logger.isLoggable(MLevel.FINE))       logger.log(MLevel.FINE,"MBean: " + oname.toString() + " registered.");
      return null;
    }
 catch (    Exception e) {
      if (logger.isLoggable(MLevel.WARNING))       logger.log(MLevel.WARNING,"An Exception occurred while registering/reregistering mbean " + mbeanName + ". MBean may not be registered, or may not work properly.",e);
      return e;
    }
  }
 catch (  NoSuchMethodException e) {
    if (logger.isLoggable(MLevel.SEVERE))     logger.log(MLevel.SEVERE,"Huh? We can't find our own constructor?? The one we're in?",e);
    return e;
  }
}
