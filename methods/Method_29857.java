public boolean isWritingQuickRebroadcast(long broadcastId){
  RebroadcastBroadcastWriter writer=findWriter(broadcastId);
  return writer != null && writer.getText() == null;
}
