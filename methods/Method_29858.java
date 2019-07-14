public String getText(long broadcastId){
  RebroadcastBroadcastWriter writer=findWriter(broadcastId);
  return writer != null ? writer.getText() : null;
}
