private List<String> getSelectColumns(){
  List<String> selectColumns=new ArrayList<>();
  if (StringUtils.isNotEmpty(inputColumns)) {
    selectColumns.addAll(partitionKeys);
    for (    String column : Splitter.on(',').split(inputColumns)) {
      if (!partitionKeys.contains(column)) {
        selectColumns.add(column);
      }
    }
  }
  return selectColumns;
}
