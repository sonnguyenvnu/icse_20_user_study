static String createTable(Fusiontables service,String name,List<String> columnNames) throws IOException {
  StringBuffer sb=new StringBuffer();
  sb.append("CREATE TABLE '");
  sb.append(name);
  sb.append("' (");
  boolean first=true;
  for (  String columnName : columnNames) {
    if (first) {
      first=false;
    }
 else {
      sb.append(',');
    }
    sb.append("'");
    sb.append(columnName);
    sb.append("': STRING");
  }
  sb.append(")");
  String createQuery=sb.toString();
  Sqlresponse response=executeQuery(service,createQuery);
  return getTableId(response);
}
