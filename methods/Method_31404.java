@Override protected void adjustDelimiter(ParserContext context,StatementType statementType){
  if (statementType == PLSQL_STATEMENT || statementType == PLSQL_VIEW_STATEMENT) {
    context.setDelimiter(PLSQL_DELIMITER);
  }
 else {
    context.setDelimiter(Delimiter.SEMICOLON);
  }
}
