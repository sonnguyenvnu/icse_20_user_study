private static String getTableId(Sqlresponse response){
  List<Object> row=response.getRows().get(0);
  int i=0;
  for (  String colname : response.getColumns()) {
    if ("tableid".equals(colname)) {
      return (String)row.get(i);
    }
  }
  return null;
}
