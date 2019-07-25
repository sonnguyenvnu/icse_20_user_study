private void error(long messageId,int state){
  if (state == VALID_ERROR) {
    Qmon.invalidMsgCountInc(dataSourceInfo.getUrl());
    LOG.error("invalid message, db: {}",dataSourceInfo.getUrl());
  }
  messageClientStore.updateError(dataSource,messageId,state);
}
