@Override public CommonPager<HmilyCompensationVO> listByPage(final CompensationQuery query){
  final String tableName=RepositoryPathUtils.buildDbTableName(query.getApplicationName());
  final PageParameter pageParameter=query.getPageParameter();
  StringBuilder sqlBuilder=new StringBuilder();
  sqlBuilder.append("select trans_id,target_class,target_method,confirm_method,cancel_method," + " retried_count,create_time,last_time,version from ").append(tableName).append(" where 1= 1 ");
  if (StringUtils.isNoneBlank(query.getTransId())) {
    sqlBuilder.append(" and trans_id = ").append(query.getTransId());
  }
  if (Objects.nonNull(query.getRetry())) {
    sqlBuilder.append(" and retried_count < ").append(query.getRetry());
  }
  final String sql=buildPageSql(sqlBuilder.toString(),pageParameter);
  CommonPager<HmilyCompensationVO> pager=new CommonPager<>();
  final List<Map<String,Object>> mapList=jdbcTemplate.queryForList(sql);
  if (CollectionUtils.isNotEmpty(mapList)) {
    pager.setDataList(mapList.stream().map(this::buildByMap).collect(Collectors.toList()));
  }
  final Integer totalCount=jdbcTemplate.queryForObject(String.format("select count(1) from %s",tableName),Integer.class);
  if (Objects.nonNull(totalCount)) {
    pager.setPage(PageHelper.buildPage(pageParameter,totalCount));
  }
  return pager;
}
