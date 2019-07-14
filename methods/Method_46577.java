@Override public void onAfterExecuteUpdate(StatementInformation statementInformation,long timeElapsedNanos,String sql,int rowCount,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
