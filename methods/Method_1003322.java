/** 
 * This method is called by the database engine once when initializing the trigger. It is called when the trigger is created, as well as when the database is opened. The default implementation initialized the result sets.
 * @param conn a connection to the database
 * @param schemaName the name of the schema
 * @param triggerName the name of the trigger used in the CREATE TRIGGERstatement
 * @param tableName the name of the table
 * @param before whether the fire method is called before or after theoperation is performed
 * @param type the operation type: INSERT, UPDATE, DELETE, SELECT, or acombination (this parameter is a bit field)
 */
@Override public void init(Connection conn,String schemaName,String triggerName,String tableName,boolean before,int type) throws SQLException {
  ResultSet rs=conn.getMetaData().getColumns(null,schemaName,tableName,null);
  oldSource=new TriggerRowSource();
  newSource=new TriggerRowSource();
  oldResultSet=new SimpleResultSet(oldSource);
  newResultSet=new SimpleResultSet(newSource);
  while (rs.next()) {
    String column=rs.getString("COLUMN_NAME");
    int dataType=rs.getInt("DATA_TYPE");
    int precision=rs.getInt("COLUMN_SIZE");
    int scale=rs.getInt("DECIMAL_DIGITS");
    oldResultSet.addColumn(column,dataType,precision,scale);
    newResultSet.addColumn(column,dataType,precision,scale);
  }
  this.schemaName=schemaName;
  this.triggerName=triggerName;
  this.tableName=tableName;
  this.before=before;
  this.type=type;
}
