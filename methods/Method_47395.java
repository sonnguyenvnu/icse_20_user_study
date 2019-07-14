/** 
 * Watches over the service progress without interrupting the worker thread in respective services Method frees up all the resources and handlers after operation completes.
 */
public void watch(ServiceWatcherInteractionInterface interactionInterface){
  runnable=new Runnable(){
    @Override public void run(){
      if (progressHandler.getFileName() == null)       handler.postDelayed(this,1000);
      if (position == progressHandler.getWrittenSize() && (state != STATE_HALTED && ++haltCounter > 5)) {
        String writtenSize=Formatter.formatShortFileSize(interactionInterface.getApplicationContext(),progressHandler.getWrittenSize());
        String totalSize=Formatter.formatShortFileSize(interactionInterface.getApplicationContext(),progressHandler.getTotalSize());
        if (interactionInterface.isDecryptService() && writtenSize.equals(totalSize)) {
          progressHandler.addWrittenLength(progressHandler.getTotalSize());
          if (!pendingIntents.isEmpty())           pendingIntents.remove();
          handler.removeCallbacks(this);
          handlerThread.quit();
          return;
        }
        haltCounter=0;
        state=STATE_HALTED;
        interactionInterface.progressHalted();
      }
 else       if (position != progressHandler.getWrittenSize()) {
        if (state == STATE_HALTED) {
          state=STATE_RESUMED;
          haltCounter=0;
          interactionInterface.progressResumed();
        }
 else {
          state=STATE_UNSET;
          haltCounter=0;
        }
      }
      progressHandler.addWrittenLength(position);
      if (position == progressHandler.getTotalSize() || progressHandler.getCancelled()) {
        if (!pendingIntents.isEmpty())         pendingIntents.remove();
        handler.removeCallbacks(this);
        handlerThread.quit();
        return;
      }
      handler.postDelayed(this,1000);
    }
  }
;
  handler.postDelayed(runnable,1000);
}
