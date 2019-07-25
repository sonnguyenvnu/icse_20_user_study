@Override public final void push(RowMap r) throws Exception {
  Position position=r.getNextPosition();
  if (!r.shouldOutput(outputConfig)) {
    inflightMessages.addMessage(position,r.getTimestampMillis(),0L);
    InflightMessageList.InflightMessage completed=inflightMessages.completeMessage(position);
    if (completed != null) {
      context.setPosition(completed.position);
    }
    return;
  }
  long messageID=inflightMessages.waitForSlot();
  if (r.isTXCommit()) {
    inflightMessages.addMessage(position,r.getTimestampMillis(),messageID);
  }
  CallbackCompleter cc=new CallbackCompleter(inflightMessages,position,r.isTXCommit(),context,messageID);
  sendAsync(r,cc);
}
