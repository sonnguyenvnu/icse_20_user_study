public boolean disable(){
  final int refCnt=this.refCnt;
  if (refCnt < 1) {
    return true;
  }
  if (refCnt > 1) {
    return false;
  }
  return REF_CNT_UPDATER.compareAndSet(this,refCnt,refCnt - 1);
}
