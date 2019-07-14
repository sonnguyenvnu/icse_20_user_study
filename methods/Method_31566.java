private void extractResults(Results results,Statement statement,boolean hasResults) throws SQLException {
  int updateCount=-1;
  while (hasResults || (updateCount=statement.getUpdateCount()) != -1) {
    results.addResult(new Result(updateCount));
    hasResults=statement.getMoreResults();
  }
}
