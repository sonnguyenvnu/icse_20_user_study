private String getIdProperty(RDBTableMetaData tableMetaData){
  return tableMetaData.getColumns().stream().filter(RDBColumnMetaData::isPrimaryKey).findFirst().map(RDBColumnMetaData::getAlias).orElseThrow(() -> new BusinessException("?[" + tableMetaData.getComment() + "]???????"));
}
