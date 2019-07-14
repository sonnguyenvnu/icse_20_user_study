@Override public void accept(final DbOomConfig dbOomConfig){
  dbOomConfig.setUpdateAcceptsTableAlias(false);
  dbOomConfig.setUpdateablePrimaryKey(false);
  dbOomConfig.getColumnNames().setQuoteChar('\"');
}
