public static void create(Map<String,Object> re,ResultSet rs,ResultSetMetaData meta){
  String name=null;
  int i=0;
  try {
    if (meta == null)     meta=rs.getMetaData();
    int count=meta.getColumnCount();
    for (i=1; i <= count; i++) {
      name=meta.getColumnLabel(i);
switch (meta.getColumnType(i)) {
case Types.TIMESTAMP:
{
          re.put(name,rs.getTimestamp(i));
          break;
        }
case Types.DATE:
{
        re.put(name,rs.getTimestamp(i));
        break;
      }
case Types.CLOB:
{
      re.put(name,rs.getString(i));
      break;
    }
case Types.BLOB:
{
    re.put(name,new BlobValueAdaptor(Jdbcs.getFilePool()).get(rs,name));
    break;
  }
default :
re.put(name,rs.getObject(i));
break;
}
}
}
 catch (SQLException e) {
if (name != null) {
throw new DaoException(String.format("Column Name=%s, index=%d",name,i),e);
}
throw new DaoException(e);
}
}
