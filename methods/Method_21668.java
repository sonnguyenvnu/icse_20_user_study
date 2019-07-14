private List<List<Map.Entry<Field,Field>>> getComparisonFields(String t1Alias,String t2Alias,Where connectedWhere) throws SqlParseException {
  List<List<Map.Entry<Field,Field>>> comparisonFields=new ArrayList<>();
  if (connectedWhere == null)   return comparisonFields;
  boolean allAnds=true;
  for (  Where innerWhere : connectedWhere.getWheres()) {
    if (innerWhere.getConn() == Where.CONN.OR) {
      allAnds=false;
      break;
    }
  }
  if (allAnds) {
    List<Map.Entry<Field,Field>> innerComparisonFields=getComparisonFieldsFromWhere(t1Alias,t2Alias,connectedWhere);
    comparisonFields.add(innerComparisonFields);
  }
 else {
    for (    Where innerWhere : connectedWhere.getWheres()) {
      comparisonFields.add(getComparisonFieldsFromWhere(t1Alias,t2Alias,innerWhere));
    }
  }
  return comparisonFields;
}
