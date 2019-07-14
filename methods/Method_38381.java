@Override public void accept(final DbOomConfig dbOomConfig){
  dbOomConfig.getTableNames().setUppercase(true);
  dbOomConfig.getColumnNames().setUppercase(true);
  dbOomConfig.getColumnNames().setQuoteChar('\"');
}
