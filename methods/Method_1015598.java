@ManagedOperation(description="Flushes all pending up and down messages. Optionally disables shuffling") public void flush(boolean stop_shuffling){
  up_lock.lock();
  try {
    if (stop_shuffling)     up=false;
    up_msgs.forEach(msg -> up_prot.up(msg));
    up_msgs.clear();
  }
  finally {
    up_lock.unlock();
  }
  down_lock.lock();
  try {
    if (stop_shuffling)     down=false;
    down_msgs.forEach(msg -> down_prot.down(msg));
    down_msgs.clear();
  }
  finally {
    down_lock.unlock();
  }
}
