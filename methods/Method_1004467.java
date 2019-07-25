@Override public boolean process(final AckMessageProcessor.AckEntry ackEntry,ActorSystem.Actor<AckMessageProcessor.AckEntry> self){
  Datagram response=RemotingBuilder.buildEmptyResponseDatagram(CommandCode.SUCCESS,ackEntry.getRequestHeader());
  try {
    if (!consumerSequenceManager.putAckActions(ackEntry)) {
      response=RemotingBuilder.buildEmptyResponseDatagram(CommandCode.BROKER_ERROR,ackEntry.getRequestHeader());
    }
  }
 catch (  Exception e) {
    response=RemotingBuilder.buildEmptyResponseDatagram(CommandCode.BROKER_ERROR,ackEntry.getRequestHeader());
  }
 finally {
    QMon.ackProcessTime(ackEntry.getSubject(),ackEntry.getGroup(),System.currentTimeMillis() - ackEntry.getAckBegin());
    ackEntry.getCtx().writeAndFlush(response);
  }
  return true;
}
