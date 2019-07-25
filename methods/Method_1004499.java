public boolean release(){
  for (; ; ) {
    final int refCnt=this.refCnt;
    if (refCnt < 1) {
      return true;
    }
    if (REF_CNT_UPDATER.compareAndSet(this,refCnt,refCnt - 1)) {
      QMon.logSegmentTotalRefCountDec();
      return true;
    }
  }
}
