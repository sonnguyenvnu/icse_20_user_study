public void run(){
  while (running) {
    Message msg=null;
    try {
      if ((msg=queue.take()) == null)       continue;
      addAndSendIfSizeExceeded(msg);
      while (true) {
        remove_queue.clear();
        int num_msgs=queue.drainTo(remove_queue);
        if (num_msgs <= 0)         break;
        for (int i=0; i < remove_queue.size(); i++) {
          msg=remove_queue.get(i);
          addAndSendIfSizeExceeded(msg);
        }
      }
      if (count > 0) {
        num_sends_because_no_msgs++;
        fill_count.add(count);
        _sendBundledMessages();
      }
    }
 catch (    Throwable t) {
    }
  }
}
