@Override protected void onWindowVisibilityChanged(int visibility){
  super.onWindowVisibilityChanged(visibility);
  if (visibility != VISIBLE && visibility != INVISIBLE) {
    cacheRunnableState=runnable;
    cacheNeedRunState=needRun;
    stopAnim();
  }
 else   if (cacheRunnableState && cacheNeedRunState) {
    startAnim();
  }
}
