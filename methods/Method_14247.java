/** 
 * Get Insert Sql
 * @return
 */
public String getInsertSQL(){
  if (logger.isDebugEnabled()) {
    logger.debug("Insert SQL with columns: {}",columns);
  }
  List<JsonNode> colOptionArray=options == null ? null : JSONUtilities.getArray(options,"columns");
  Map<String,JsonNode> colOptionsMap=new HashMap<>();
  if (colOptionArray != null) {
    colOptionArray.forEach(json -> {
      colOptionsMap.put(JSONUtilities.getString(json,"name",null),json);
    }
);
  }
  boolean nullValueNull=options == null ? true : JSONUtilities.getBoolean(options,"convertNulltoEmptyString",true);
  StringBuffer values=new StringBuffer();
  int idx=0;
  for (  ArrayList<SqlData> sqlRow : sqlDataList) {
    StringBuilder rowValue=new StringBuilder();
    for (    SqlData val : sqlRow) {
      JsonNode jsonOb=colOptionsMap.get(val.getColumnName());
      String type=JSONUtilities.getString(jsonOb,"type",null);
      String defaultValue=JSONUtilities.getString(jsonOb,"defaultValue",null);
      boolean allowNullChkBox=JSONUtilities.getBoolean(jsonOb,"defaultValue",true);
      ;
      if (type == null) {
        type=SqlData.SQL_TYPE_VARCHAR;
      }
      if (type.equals(SqlData.SQL_TYPE_VARCHAR) || type.equals(SqlData.SQL_TYPE_CHAR) || type.equals(SqlData.SQL_TYPE_TEXT)) {
        if ((val.getText() == null || val.getText().isEmpty())) {
          handleNullField(allowNullChkBox,defaultValue,nullValueNull,val.getColumnName(),rowValue,true);
        }
 else {
          rowValue.append("'" + val.getText().replace("'","''") + "'");
        }
      }
 else       if (type.equals(SqlData.SQL_TYPE_INT) || type.equals(SqlData.SQL_TYPE_INTEGER) || type.equals(SqlData.SQL_TYPE_NUMERIC)) {
        if ((val.getText() == null || val.getText().isEmpty())) {
          handleNullField(allowNullChkBox,defaultValue,nullValueNull,val.getColumnName(),rowValue,false);
        }
 else {
          if (type.equals(SqlData.SQL_TYPE_NUMERIC)) {
            if (!NumberUtils.isNumber(val.getText())) {
              throw new SqlExporterException(val.getText() + " is not compatible with column type :" + type);
            }
          }
 else {
            try {
              Integer.parseInt(val.getText());
            }
 catch (            NumberFormatException nfe) {
              throw new SqlExporterException(val.getText() + " is not compatible with column type :" + type);
            }
          }
          rowValue.append(val.getText());
        }
      }
 else       if (type.equals(SqlData.SQL_TYPE_DATE) || type.equals(SqlData.SQL_TYPE_TIMESTAMP)) {
        if ((val.getText() == null || val.getText().isEmpty())) {
          handleNullField(allowNullChkBox,defaultValue,nullValueNull,val.getColumnName(),rowValue,true);
        }
 else {
          rowValue.append("'" + val.getText() + "'");
        }
      }
      rowValue.append(",");
    }
    idx++;
    String rowValString=rowValue.toString();
    rowValString=rowValString.substring(0,rowValString.length() - 1);
    values.append("( ");
    values.append(rowValString);
    values.append(" )");
    if (idx < sqlDataList.size()) {
      values.append(",");
    }
    values.append("\n");
  }
  boolean trimColNames=options == null ? false : JSONUtilities.getBoolean(options,"trimColumnNames",false);
  String colNamesWithSep=columns.stream().map(col -> col.replaceAll("\\s","")).collect(Collectors.joining(","));
  ;
  if (!trimColNames) {
    colNamesWithSep=columns.stream().collect(Collectors.joining(","));
  }
  String valuesString=values.toString();
  valuesString=valuesString.substring(0,valuesString.length() - 1);
  StringBuffer sql=new StringBuffer();
  sql.append("INSERT INTO ").append(table);
  sql.append(" (");
  sql.append(colNamesWithSep);
  sql.append(") VALUES ").append("\n");
  sql.append(valuesString);
  String sqlString=sql.toString();
  if (logger.isDebugEnabled()) {
    logger.debug("Insert Statement Generated Successfully...{}",sqlString);
  }
  return sqlString;
}
