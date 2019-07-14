@Override public CommonPager<HmilyCompensationVO> listByPage(final CompensationQuery query){
  CommonPager<HmilyCompensationVO> commonPager=new CommonPager<>();
  final String redisKeyPrefix=RepositoryPathUtils.buildRedisKeyPrefix(query.getApplicationName());
  final int currentPage=query.getPageParameter().getCurrentPage();
  final int pageSize=query.getPageParameter().getPageSize();
  int start=(currentPage - 1) * pageSize;
  Set<byte[]> keys;
  List<HmilyCompensationVO> voList;
  int totalCount;
  if (StringUtils.isBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
    keys=jedisClient.keys((redisKeyPrefix + "*").getBytes());
    final List<HmilyCompensationVO> all=findAll(keys);
    final List<HmilyCompensationVO> collect=all.stream().filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
    totalCount=collect.size();
    voList=collect.stream().skip(start).limit(pageSize).collect(Collectors.toList());
  }
 else   if (StringUtils.isNoneBlank(query.getTransId()) && Objects.isNull(query.getRetry())) {
    keys=Sets.newHashSet(String.join(":",redisKeyPrefix,query.getTransId()).getBytes());
    totalCount=keys.size();
    voList=findAll(keys);
  }
 else   if (StringUtils.isNoneBlank(query.getTransId()) && Objects.nonNull(query.getRetry())) {
    keys=Sets.newHashSet(String.join(":",redisKeyPrefix,query.getTransId()).getBytes());
    totalCount=keys.size();
    voList=findAll(keys).stream().filter(vo -> vo.getRetriedCount() < query.getRetry()).collect(Collectors.toList());
  }
 else {
    keys=jedisClient.keys((redisKeyPrefix + "*").getBytes());
    if (keys.size() <= 0 || keys.size() < start) {
      return commonPager;
    }
    totalCount=keys.size();
    voList=findByPage(keys,start,pageSize);
  }
  if (keys.size() <= 0 || keys.size() < start) {
    return commonPager;
  }
  commonPager.setPage(PageHelper.buildPage(query.getPageParameter(),totalCount));
  commonPager.setDataList(voList);
  return commonPager;
}
