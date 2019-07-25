/** 
 * Update a row using a UPDATE statement. This method is to be called if the emit updates option is enabled.
 * @param oldRow the old data
 * @param newRow the new data
 */
public void update(Row oldRow,Row newRow){
  ArrayList<Value> params=Utils.newSmallArrayList();
  StringBuilder builder=new StringBuilder("UPDATE ").append(targetTableName).append(" SET ");
  for (int i=0; i < newRow.getColumnCount(); i++) {
    if (i > 0) {
      builder.append(", ");
    }
    table.getColumn(i).getSQL(builder,quoteAllIdentifiers).append('=');
    Value v=newRow.getValue(i);
    if (v == null) {
      builder.append("DEFAULT");
    }
 else {
      builder.append('?');
      params.add(v);
    }
  }
  builder.append(" WHERE ");
  for (int i=0; i < oldRow.getColumnCount(); i++) {
    Column col=table.getColumn(i);
    if (i > 0) {
      builder.append(" AND ");
    }
    col.getSQL(builder,quoteAllIdentifiers);
    Value v=oldRow.getValue(i);
    if (isNull(v)) {
      builder.append(" IS NULL");
    }
 else {
      builder.append('=');
      params.add(v);
      addParameter(builder,col);
    }
  }
  String sql=builder.toString();
  try {
    link.execute(sql,params,true);
  }
 catch (  Exception e) {
    throw TableLink.wrapException(sql,e);
  }
}
