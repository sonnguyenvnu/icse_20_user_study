@Override public void onAfterExecute(PreparedStatementInformation statementInformation,long timeElapsedNanos,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
