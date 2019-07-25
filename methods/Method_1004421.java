private void offer(ReceivedDelayMessage message,ReceivedResult result){
  if (MessageProducerCode.SUCCESS != result.getCode()) {
    end(message,result);
    return;
  }
  if (!shouldWaitSlave()) {
    end(message,result);
    return;
  }
  waitSlaveSyncQueue.addLast(new ReceiveEntry(message,result));
}
