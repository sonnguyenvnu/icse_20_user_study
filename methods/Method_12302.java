private void scheduleNextRender(){
  if (mIsRenderingTriggeredOnDraw && mIsRunning && mNextFrameRenderTime != Long.MIN_VALUE) {
    final long renderDelay=Math.max(0,mNextFrameRenderTime - SystemClock.uptimeMillis());
    mNextFrameRenderTime=Long.MIN_VALUE;
    mExecutor.remove(mRenderTask);
    mRenderTaskSchedule=mExecutor.schedule(mRenderTask,renderDelay,TimeUnit.MILLISECONDS);
  }
}
