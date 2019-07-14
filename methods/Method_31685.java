protected void handleResults(Results results){
  for (  Result result : results.getResults()) {
    long updateCount=result.getUpdateCount();
    if (updateCount != -1) {
      handleUpdateCount(updateCount);
    }
  }
}
