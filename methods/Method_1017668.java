@Override public void release(byte[] buffer){
synchronized (this) {
    bufferQueue.addLast(buffer);
  }
}
