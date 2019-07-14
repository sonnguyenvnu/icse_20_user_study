@Override public void accept(final DbOomConfig dbOomConfig){
  dbOomConfig.setUpdateAcceptsTableAlias(false);
  dbOomConfig.getColumnNames().setQuoteChar('\"');
}
