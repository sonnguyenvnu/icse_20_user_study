public synchronized Object[] messages(){
synchronized (this) {
    Object[] result=new Object[numMsgs];
    for (int i=0; i < numMsgs; i++) {
      result[i]=msgs[(icons + i) % msgs.length];
    }
    return result;
  }
}
