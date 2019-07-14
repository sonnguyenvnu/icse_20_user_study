@Override public void onAfterExecuteBatch(StatementInformation statementInformation,long timeElapsedNanos,int[] updateCounts,SQLException e){
  statementInformation.incrementTimeElapsed(timeElapsedNanos);
}
