public String buildWhereForUpdate(String resultMapId,String tableName,Object param){
  String where=buildWhere(resultMapId,tableName,param);
  if (where.trim().isEmpty()) {
    throw new BusinessException("????????????");
  }
  return where;
}
