@Override public void onAfterExecuteBatch(StatementInformation statementInformation,long timeElapsedNanos,int[] updateCounts,SQLException e){
  DefaultEventListener.INSTANCE.onAfterExecuteBatch(statementInformation,timeElapsedNanos,updateCounts,e);
  p6spyEventListener.onAfterExecuteBatch(statementInformation,timeElapsedNanos,updateCounts,e);
}
