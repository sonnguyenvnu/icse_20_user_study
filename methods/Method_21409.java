public void unregisterMbean(){
  if (mbeanRegistered) {
    AccessController.doPrivileged(new PrivilegedAction<Object>(){
      @Override public Object run(){
        DruidDataSourceStatManager.removeDataSource(ElasticSearchDruidDataSource.this);
        ElasticSearchDruidDataSource.this.mbeanRegistered=false;
        return null;
      }
    }
);
  }
}
