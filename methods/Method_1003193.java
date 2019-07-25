private void swap(int a,int b,int free){
  if (a < MIN_PAGE_COUNT || b < MIN_PAGE_COUNT) {
    System.out.println(isUsed(a) + " " + isUsed(b));
    DbException.throwInternalError("can't swap " + a + " and " + b);
  }
  Page f=(Page)cache.get(free);
  if (f != null) {
    DbException.throwInternalError("not free: " + f);
  }
  if (trace.isDebugEnabled()) {
    trace.debug("swap " + a + " and " + b + " via " + free);
  }
  Page pageA=null;
  if (isUsed(a)) {
    pageA=getPage(a);
    if (pageA != null) {
      pageA.moveTo(pageStoreSession,free);
    }
    free(a);
  }
  if (free != b) {
    if (isUsed(b)) {
      Page pageB=getPage(b);
      if (pageB != null) {
        pageB.moveTo(pageStoreSession,a);
      }
      free(b);
    }
    if (pageA != null) {
      f=getPage(free);
      if (f != null) {
        f.moveTo(pageStoreSession,b);
      }
      free(free);
    }
  }
}
