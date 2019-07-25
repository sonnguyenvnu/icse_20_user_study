public final boolean put(long fp) throws IOException {
  fp=checkValid(fp);
  long fp0=fp & 0x7FFFFFFFFFFFFFFFL;
  final Lock readLock=rwLock.getAt(getLockIndex(fp0)).readLock();
  readLock.lock();
  if (this.memLookup(fp0)) {
    readLock.unlock();
    this.memHitCnt.increment();
    return true;
  }
  boolean diskHit=this.diskLookup(fp0);
  if (diskHit) {
    readLock.unlock();
    this.diskHitCnt.increment();
    return true;
  }
  readLock.unlock();
  final Lock w=rwLock.getAt(getLockIndex(fp0)).writeLock();
  w.lock();
  if (this.memInsert(fp0)) {
    w.unlock();
    this.memHitCnt.increment();
    return true;
  }
  if (needsDiskFlush() && this.flusherChosen.compareAndSet(false,true)) {
    growDiskMark++;
    final long timestamp=System.currentTimeMillis();
    final long insertions=getTblCnt();
    rwLock.acquireAllLocks();
    flusher.flushTable();
    rwLock.releaseAllLocks();
    forceFlush=false;
    this.flusherChosen.set(false);
    long l=System.currentTimeMillis() - timestamp;
    flushTime+=l;
    LOGGER.log(Level.FINE,"Flushed disk {0} {1}. time, in {2} sec after {3} insertions.",new Object[]{((DiskFPSetMXWrapper)diskFPSetMXWrapper).getObjectName(),getGrowDiskMark(),l,insertions});
  }
  w.unlock();
  return false;
}
