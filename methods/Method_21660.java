private static void handleSubQueries(Client client,Select select) throws SqlParseException {
  if (select.containsSubQueries()) {
    for (    SubQueryExpression subQueryExpression : select.getSubQueries()) {
      QueryAction queryAction=handleSelect(client,subQueryExpression.getSelect());
      executeAndFillSubQuery(client,subQueryExpression,queryAction);
    }
  }
}
