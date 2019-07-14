private static void restoreData(StandardDatabase db,Class<? extends AbstractDao<?,?>>... daoClasses){
  for (int i=0; i < daoClasses.length; i++) {
    DaoConfig daoConfig=new DaoConfig(db,daoClasses[i]);
    String tableName=daoConfig.tablename;
    String tempTableName=daoConfig.tablename.concat("_TEMP");
    List<String> columns=getColumns(db,tempTableName);
    ArrayList<String> properties=new ArrayList<>(columns.size());
    for (int j=0; j < daoConfig.properties.length; j++) {
      String columnName=daoConfig.properties[j].columnName;
      if (columns.contains(columnName)) {
        properties.add(columnName);
      }
    }
    if (properties.size() > 0) {
      final String columnSQL=TextUtils.join(",",properties);
      StringBuilder insertTableStringBuilder=new StringBuilder();
      insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");
      insertTableStringBuilder.append(columnSQL);
      insertTableStringBuilder.append(") SELECT ");
      insertTableStringBuilder.append(columnSQL);
      insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");
      db.execSQL(insertTableStringBuilder.toString());
    }
    StringBuilder dropTableStringBuilder=new StringBuilder();
    dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);
    db.execSQL(dropTableStringBuilder.toString());
  }
}
