protected ParsedSqlStatement createStatement(PeekingReader reader,Recorder recorder,int statementPos,int statementLine,int statementCol,int nonCommentPartPos,int nonCommentPartLine,int nonCommentPartCol,StatementType statementType,boolean canExecuteInTransaction,Delimiter delimiter,String sql) throws IOException {
  return new ParsedSqlStatement(statementPos,statementLine,statementCol,sql,delimiter,canExecuteInTransaction);
}
