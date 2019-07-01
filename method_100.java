private void _XXXXX_(ASTNode ast){
  int len=ast.getChildCount();
  if (len > 0) {
    for (    Node n : ast.getChildren()) {
      ASTNode asn=(ASTNode)n;
switch (asn.getToken().getType()) {
case HiveParser.TOK_TABNAME:
        parserContent.getTableColumnMap().put(ast.getChild(0).getChild(0).getText(),new HashSet<>(columnSet));
      break;
case HiveParser.TOK_SET_COLUMNS_CLAUSE:
    for (int i=0; i < asn.getChildCount(); i++) {
      addToColumnSet((ASTNode)asn.getChild(i).getChild(0));
    }
  break;
case HiveParser.TOK_QUERY:
parseQueryClause(asn);
break;
case HiveParser.TOK_UNIONTYPE:
case HiveParser.TOK_UNIONALL:
case HiveParser.TOK_UNIONDISTINCT:
visitSubtree(asn);
break;
}
}
addTablesColumnsToMap(tableSet,columnSet);
}
}