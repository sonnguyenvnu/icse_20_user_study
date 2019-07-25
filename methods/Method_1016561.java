public String format(final String argSql){
  statementDelimiters.add(formatterCfg.getStatementDelimiter());
  SQLTokensParser fParser=new SQLTokensParser();
  functionBracket.clear();
  boolean isSqlEndsWithNewLine=false;
  if (argSql.endsWith("\n")) {
    isSqlEndsWithNewLine=true;
  }
  List<FormatterToken> list=fParser.parse(argSql);
  list=format(list);
  StringBuilder after=new StringBuilder(argSql.length() + 20);
  for (  FormatterToken token : list) {
    after.append(token.getString());
  }
  if (isSqlEndsWithNewLine) {
    after.append(getDefaultLineSeparator());
  }
  return after.toString();
}
