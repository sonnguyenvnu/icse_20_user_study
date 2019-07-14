@Override public CommonPager<HmilyCompensationVO> listByPage(final CompensationQuery query){
  CommonPager<HmilyCompensationVO> voCommonPager=new CommonPager<>();
  final int currentPage=query.getPageParameter().getCurrentPage();
  final int pageSize=query.getPageParameter().getPageSize();
  int start=(currentPage - 1) * pageSize;
  final String rootPath=RepositoryPathUtils.buildZookeeperPathPrefix(query.getApplicationName());
  List<String> zNodePaths;
  List<HmilyCompensationVO> voList;
  int totalCount;
  try {
    if (StringUtils.isBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
      zNodePaths=zooKeeper.getChildren(rootPath,false);
      final List<HmilyCompensationVO> all=findAll(zNodePaths,rootPath);
      final List<HmilyCompensationVO> collect=all.stream().filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
      totalCount=collect.size();
      voList=collect.stream().skip(start).limit(pageSize).collect(Collectors.toList());
    }
 else     if (StringUtils.isNoneBlank(query.getTransId()) && Objects.isNull(query.getRetry())) {
      zNodePaths=Lists.newArrayList(query.getTransId());
      totalCount=zNodePaths.size();
      voList=findAll(zNodePaths,rootPath);
    }
 else     if (StringUtils.isNoneBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
      zNodePaths=Lists.newArrayList(query.getTransId());
      totalCount=zNodePaths.size();
      voList=findAll(zNodePaths,rootPath).stream().filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
    }
 else {
      zNodePaths=zooKeeper.getChildren(rootPath,false);
      totalCount=zNodePaths.size();
      voList=findByPage(zNodePaths,rootPath,start,pageSize);
    }
    voCommonPager.setPage(PageHelper.buildPage(query.getPageParameter(),totalCount));
    voCommonPager.setDataList(voList);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return voCommonPager;
}
