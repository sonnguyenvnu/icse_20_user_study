@Override public void onAfterExecuteUpdate(StatementInformation statementInformation,long timeElapsedNanos,String sql,int rowCount,SQLException e){
  if (statementInformation.getAttachment() instanceof Insert) {
    try {
      sqlExecuteInterceptor.postInsert(statementInformation);
    }
 catch (    SQLException e1) {
      throw new RuntimeException(e1);
    }
  }
}
