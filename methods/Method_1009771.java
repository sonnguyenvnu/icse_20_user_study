public boolean destroy(){
synchronized (syncOp) {
    audioRecord.release();
    return true;
  }
}
