private List<Field> convertExprsToFields(List<? extends SQLExpr> exprs,SQLTableSource sqlTableSource) throws SqlParseException {
  List<Field> fields=new ArrayList<>(exprs.size());
  for (  SQLExpr expr : exprs) {
    fields.add(FieldMaker.makeField(expr,null,sqlTableSource.getAlias()));
  }
  return fields;
}
