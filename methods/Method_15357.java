@Override protected Object getValue(SQLConfig config,ResultSet rs,ResultSetMetaData rsmd,int tablePosition,JSONObject table,int columnIndex,Map<String,JSONObject> childMap) throws Exception {
  Object value=super.getValue(config,rs,rsmd,tablePosition,table,columnIndex,childMap);
  if (value instanceof Blob) {
    value=new String(((Blob)value).getBytes(1,(int)((Blob)value).length()),"UTF-8");
  }
 else   if (value instanceof Clob) {
    StringBuffer sb=new StringBuffer();
    BufferedReader br=new BufferedReader(((Clob)value).getCharacterStream());
    String s=br.readLine();
    while (s != null) {
      sb.append(s);
      s=br.readLine();
    }
    value=sb.toString();
  }
 else   if (value instanceof PGobject) {
    value=JSON.parse(((PGobject)value).getValue());
  }
  return value;
}
