public final boolean contains(long fp) throws IOException {
  fp=checkValid(fp);
  long fp0=fp & 0x7FFFFFFFFFFFFFFFL;
  final Lock readLock=this.rwLock.getAt(getLockIndex(fp0)).readLock();
  readLock.lock();
  if (this.memLookup(fp0)) {
    readLock.unlock();
    this.memHitCnt.increment();
    return true;
  }
  boolean diskHit=this.diskLookup(fp0);
  if (diskHit) {
    diskHitCnt.increment();
  }
  readLock.unlock();
  return diskHit;
}
