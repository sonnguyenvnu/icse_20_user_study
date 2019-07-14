@Override public ZookeeperDataSource getObject() throws Exception {
  if (StringUtils.isNotEmpty(groupId) && StringUtils.isNotEmpty(dataId)) {
    return new ZookeeperDataSource(serverAddr,groupId,dataId,converter);
  }
 else {
    return new ZookeeperDataSource(serverAddr,path,converter);
  }
}
