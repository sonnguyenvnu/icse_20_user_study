private void startConnectingSound(){
  if (spPlayID != 0)   soundPool.stop(spPlayID);
  spPlayID=soundPool.play(spConnectingId,1,1,0,-1,1);
  if (spPlayID == 0) {
    AndroidUtilities.runOnUIThread(connectingSoundRunnable=new Runnable(){
      @Override public void run(){
        if (sharedInstance == null)         return;
        if (spPlayID == 0)         spPlayID=soundPool.play(spConnectingId,1,1,0,-1,1);
        if (spPlayID == 0)         AndroidUtilities.runOnUIThread(this,100);
 else         connectingSoundRunnable=null;
      }
    }
,100);
  }
}
