public boolean retain(){
  for (; ; ) {
    final int refCnt=this.refCnt;
    if (refCnt < 1) {
      return false;
    }
    if (REF_CNT_UPDATER.compareAndSet(this,refCnt,refCnt + 1)) {
      QMon.logSegmentTotalRefCountInc();
      return true;
    }
  }
}
