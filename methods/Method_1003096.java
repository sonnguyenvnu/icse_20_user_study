@Override public Cursor find(Session session,SearchRow first,SearchRow last){
  ArrayList<Value> params=Utils.newSmallArrayList();
  StringBuilder builder=new StringBuilder("SELECT * FROM ").append(targetTableName).append(" T");
  boolean f=false;
  for (int i=0; first != null && i < first.getColumnCount(); i++) {
    Value v=first.getValue(i);
    if (v != null) {
      builder.append(f ? " AND " : " WHERE ");
      f=true;
      Column col=table.getColumn(i);
      col.getSQL(builder,quoteAllIdentifiers);
      if (v == ValueNull.INSTANCE) {
        builder.append(" IS NULL");
      }
 else {
        builder.append(">=");
        addParameter(builder,col);
        params.add(v);
      }
    }
  }
  for (int i=0; last != null && i < last.getColumnCount(); i++) {
    Value v=last.getValue(i);
    if (v != null) {
      builder.append(f ? " AND " : " WHERE ");
      f=true;
      Column col=table.getColumn(i);
      col.getSQL(builder,quoteAllIdentifiers);
      if (v == ValueNull.INSTANCE) {
        builder.append(" IS NULL");
      }
 else {
        builder.append("<=");
        addParameter(builder,col);
        params.add(v);
      }
    }
  }
  String sql=builder.toString();
  try {
    PreparedStatement prep=link.execute(sql,params,false);
    ResultSet rs=prep.getResultSet();
    return new LinkedCursor(link,rs,session,sql,prep);
  }
 catch (  Exception e) {
    throw TableLink.wrapException(sql,e);
  }
}
