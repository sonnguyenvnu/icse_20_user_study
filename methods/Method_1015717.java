/** 
 * Nulls elements between low and seqno and forwards low 
 */
public void stable(long seqno){
  lock.lock();
  try {
    if (seqno <= low)     return;
    if (seqno > hd)     throw new IllegalArgumentException("seqno " + seqno + " cannot be bigger than hd (" + hd + ")");
    int from=index(low + 1), length=(int)(seqno - low), capacity=capacity();
    for (int i=from; i < from + length; i++) {
      int index=i & (capacity - 1);
      buf[index]=null;
    }
    if (seqno > low) {
      low=seqno;
      buffer_full.signalAll();
    }
  }
  finally {
    lock.unlock();
  }
}
