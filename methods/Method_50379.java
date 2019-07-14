@Override public CommonPager<HmilyCompensationVO> listByPage(final CompensationQuery query){
  CommonPager<HmilyCompensationVO> voCommonPager=new CommonPager<>();
  final String mongoTableName=RepositoryPathUtils.buildMongoTableName(query.getApplicationName());
  final PageParameter pageParameter=query.getPageParameter();
  final int pageSize=pageParameter.getPageSize();
  Query baseQuery=new Query();
  if (StringUtils.isNoneBlank(query.getTransId())) {
    baseQuery.addCriteria(new Criteria("transId").is(query.getTransId()));
  }
  if (Objects.nonNull(query.getRetry())) {
    baseQuery.addCriteria(new Criteria("retriedCount").lt(query.getRetry()));
  }
  final long totalCount=mongoTemplate.count(baseQuery,mongoTableName);
  if (totalCount <= 0) {
    return voCommonPager;
  }
  final int currentPage=pageParameter.getCurrentPage();
  int start=(currentPage - 1) * pageSize;
  voCommonPager.setPage(PageHelper.buildPage(query.getPageParameter(),(int)totalCount));
  baseQuery.skip(start).limit(pageSize);
  final List<MongoAdapter> mongoAdapters=mongoTemplate.find(baseQuery,MongoAdapter.class,mongoTableName);
  if (CollectionUtils.isNotEmpty(mongoAdapters)) {
    final List<HmilyCompensationVO> recoverVOS=mongoAdapters.stream().map(ConvertHelper::buildVO).collect(Collectors.toList());
    voCommonPager.setDataList(recoverVOS);
  }
  return voCommonPager;
}
