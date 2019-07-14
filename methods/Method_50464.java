@Override public List<HmilyTransaction> listAllByDelay(final Date date){
  String sb="select * from " + tableName + " where last_time <?";
  List<Map<String,Object>> list=executeQuery(sb,date);
  if (CollectionUtils.isNotEmpty(list)) {
    return list.stream().filter(Objects::nonNull).map(this::buildByResultMap).collect(Collectors.toList());
  }
  return Collections.emptyList();
}
