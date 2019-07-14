@Override public void onAfterExecute(StatementInformation statementInformation,long timeElapsedNanos,String sql,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
