public void gain(int byteCount){
synchronized (syncByteList) {
    long now=System.currentTimeMillis();
    byteList.addLast(new ByteFrame(now,byteCount));
    trim(now);
  }
}
