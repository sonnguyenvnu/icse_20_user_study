public void run(){
  try {
    int drained=rb.drainToBlocking(remove_queue);
    if (drained == 1) {
      output.position(0);
      sendSingleMessage(remove_queue[0]);
      return;
    }
    for (int i=0; i < drained; i++) {
      Message msg=remove_queue[i];
      long size=msg.size();
      if (count + size >= transport.getMaxBundleSize())       sendBundledMessages();
      addMessage(msg,msg.size());
    }
    sendBundledMessages();
  }
 catch (  Throwable t) {
  }
}
