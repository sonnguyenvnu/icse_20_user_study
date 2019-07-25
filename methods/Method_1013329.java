public final boolean contains(final long fp) throws IOException {
  if (checkEvictPending()) {
    return contains(fp);
  }
  final long fp0=fp & FLUSHED_MASK;
  if (memLookup(fp0)) {
    return true;
  }
  if (this.diskLookup(fp0)) {
    diskHitCnt.increment();
    return true;
  }
  return false;
}
