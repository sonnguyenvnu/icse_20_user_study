@Override public void onAfterGetResultSet(StatementInformation statementInformation,long timeElapsedNanos,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
