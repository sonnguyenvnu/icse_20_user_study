public void reset(){
  lock.lock();
  try {
    num_blockings=0;
    avg_block_time.clear();
  }
  finally {
    lock.unlock();
  }
}
