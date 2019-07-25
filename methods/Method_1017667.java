@Override public byte[] allocate(int size){
synchronized (this) {
    if (bufferQueue.isEmpty()) {
      return new byte[size];
    }
 else {
      return bufferQueue.pollFirst();
    }
  }
}
