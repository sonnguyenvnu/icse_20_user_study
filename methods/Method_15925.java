static void rePaging(int total){
  Pager pager=get();
  int pageIndex=0;
  if (pager != null) {
    if (pager.pageIndex() != 0 && (pager.pageIndex() * pager.pageSize()) >= total) {
      int tmp=total / pager.pageSize();
      pageIndex=total % pager.pageSize() == 0 ? tmp - 1 : tmp;
    }
    doPaging(pageIndex,pager.pageSize());
  }
}
