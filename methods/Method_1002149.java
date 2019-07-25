@Override public void generator(List<ColumnInfo> columnInfos,GenConfig genConfig,String tableName){
  if (genConfig.getId() == null) {
    throw new BadRequestException("???????");
  }
  try {
    GenUtil.generatorCode(columnInfos,genConfig,tableName);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
