public void run(){
  while (true) {
    try {
      PendingKey k;
      while (!pending.isEmpty()) {
        k=pending.poll();
        if (k.Op == PendingKey.OP_WRITE) {
          if (k.key.isValid()) {
            k.key.interestOps(OP_WRITE);
          }
        }
 else {
          closeKey(k.key,k.Op);
        }
      }
      if (selector.select() <= 0) {
        continue;
      }
      Set<SelectionKey> selectedKeys=selector.selectedKeys();
      for (      SelectionKey key : selectedKeys) {
        if (!key.isValid()) {
          continue;
        }
        if (key.isAcceptable()) {
          accept(key);
        }
 else         if (key.isReadable()) {
          doRead(key);
        }
 else         if (key.isWritable()) {
          doWrite(key);
        }
      }
      selectedKeys.clear();
    }
 catch (    ClosedSelectorException ignore) {
      return;
    }
catch (    Throwable e) {
      errorLogger.log("http server loop error, should not happen",e);
      eventLogger.log(eventNames.serverLoopError);
    }
  }
}
