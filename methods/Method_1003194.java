private boolean compact(int full,int free){
  if (full < MIN_PAGE_COUNT || free == -1 || free >= full || !isUsed(full)) {
    return false;
  }
  Page f=(Page)cache.get(free);
  if (f != null) {
    DbException.throwInternalError("not free: " + f);
  }
  Page p=getPage(full);
  if (p == null) {
    freePage(full);
  }
 else   if (p instanceof PageStreamData || p instanceof PageStreamTrunk) {
    if (p.getPos() < log.getMinPageId()) {
      freePage(full);
    }
  }
 else {
    if (trace.isDebugEnabled()) {
      trace.debug("move " + p.getPos() + " to " + free);
    }
    try {
      p.moveTo(pageStoreSession,free);
    }
  finally {
      if (++changeCount < 0) {
        throw DbException.throwInternalError("changeCount has wrapped");
      }
    }
  }
  return true;
}
