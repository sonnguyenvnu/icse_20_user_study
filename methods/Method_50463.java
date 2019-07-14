@Override @SuppressWarnings("unchecked") public List<HmilyTransaction> listAll(){
  String selectSql="select * from " + tableName;
  List<Map<String,Object>> list=executeQuery(selectSql);
  if (CollectionUtils.isNotEmpty(list)) {
    return list.stream().filter(Objects::nonNull).map(this::buildByResultMap).collect(Collectors.toList());
  }
  return Collections.emptyList();
}
