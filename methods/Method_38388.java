@Override public void accept(final DbOomConfig dbOomConfig){
  dbOomConfig.getTableNames().setLowercase(true);
  dbOomConfig.getColumnNames().setLowercase(true);
  dbOomConfig.getColumnNames().setQuoteChar('\"');
}
