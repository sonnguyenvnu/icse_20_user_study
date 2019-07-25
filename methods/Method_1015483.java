public void run(){
  while (running) {
    Message msg=null;
    try {
      if ((msg=queue.take()) == null)       continue;
      long size=msg.size();
      if (count + size >= transport.getMaxBundleSize()) {
        num_sends_because_full_queue++;
        fill_count.add(count);
        _sendBundledMessages();
      }
      for (; ; ) {
        Address dest=msg.dest();
        if (!Util.match(dest,target_dest) || count + size >= transport.getMaxBundleSize())         _sendBundledMessages();
        _addMessage(msg,size);
        msg=queue.poll();
        if (msg == null)         break;
        size=msg.size();
      }
      _sendBundledMessages();
    }
 catch (    Throwable t) {
    }
  }
}
