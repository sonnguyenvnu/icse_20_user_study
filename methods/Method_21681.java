@Override public String explain(){
  String baseExplain=super.explain();
  Where where=this.connectedWhere;
  QueryBuilder explan=null;
  try {
    if (where != null)     explan=QueryMaker.explan(where,false);
  }
 catch (  SqlParseException e) {
  }
  String conditions=explan == null ? "Could not parse conditions" : explan.toString();
  String nestedExplain="Nested Loops \n run first query , and for each result run second query with additional conditions :\n" + conditions + "\n" + baseExplain;
  return nestedExplain;
}
