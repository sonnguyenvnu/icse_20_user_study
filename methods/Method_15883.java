@Override public SQL render(RDBTableMetaData metaData,UpdateParam param){
  RDBTableMetaData metaDataNew=metaData.clone();
  metaDataNew.setDatabaseMetaData(metaData.getDatabaseMetaData());
  metaDataNew.getColumns().stream().filter(column -> column.getName().contains(".")).map(RDBColumnMetaData::getName).forEach(metaDataNew::removeColumn);
  return super.render(metaDataNew,param);
}
