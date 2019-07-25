public void done(int work){
  if (myMonitor != null) {
    myMonitor.advance(work);
    myMonitor.done();
  }
  if (!myCurrentStartMsg.isEmpty()) {
    myTracer.pop();
  }
}
