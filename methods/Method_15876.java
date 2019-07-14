public String buildUpdateSql(String resultMapId,String tableName,UpdateParam param){
  Pager.reset();
  RDBTableMetaData tableMetaData=createMeta(tableName,resultMapId);
  SqlRender<UpdateParam> render=tableMetaData.getDatabaseMetaData().getRenderer(SqlRender.TYPE.UPDATE);
  return render.render(tableMetaData,param).getSql();
}
