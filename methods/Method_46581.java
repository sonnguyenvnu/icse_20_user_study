@Override public void onAfterResultSetNext(ResultSetInformation resultSetInformation,long timeElapsedNanos,boolean hasNext,SQLException e){
  resultSetInformation.getStatementInformation().incrementTimeElapsed(timeElapsedNanos);
  if (hasNext) {
    resultSetInformation.incrementCurrRow();
  }
}
