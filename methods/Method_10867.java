private static void generateTempTables(StandardDatabase db,Class<? extends AbstractDao<?,?>>... daoClasses){
  for (int i=0; i < daoClasses.length; i++) {
    DaoConfig daoConfig=new DaoConfig(db,daoClasses[i]);
    String tableName=daoConfig.tablename;
    String tempTableName=daoConfig.tablename.concat("_TEMP");
    StringBuilder insertTableStringBuilder=new StringBuilder();
    insertTableStringBuilder.append("CREATE TEMP TABLE ").append(tempTableName);
    insertTableStringBuilder.append(" AS SELECT * FROM ").append(tableName).append(";");
    db.execSQL(insertTableStringBuilder.toString());
  }
}
