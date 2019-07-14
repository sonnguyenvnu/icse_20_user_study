private void dumpDirectly(final String path,final List<String> result){
  for (  String each : regCenter.getChildrenKeys(path)) {
    String zkPath=path + "/" + each;
    String zkValue=regCenter.get(zkPath);
    if (null == zkValue) {
      zkValue="";
    }
    TreeCache treeCache=(TreeCache)regCenter.getRawCache("/" + jobName);
    ChildData treeCacheData=treeCache.getCurrentData(zkPath);
    String treeCachePath=null == treeCacheData ? "" : treeCacheData.getPath();
    String treeCacheValue=null == treeCacheData ? "" : new String(treeCacheData.getData());
    if (zkValue.equals(treeCacheValue) && zkPath.equals(treeCachePath)) {
      result.add(Joiner.on(" | ").join(zkPath,zkValue));
    }
 else {
      result.add(Joiner.on(" | ").join(zkPath,zkValue,treeCachePath,treeCacheValue));
    }
    dumpDirectly(zkPath,result);
  }
}
