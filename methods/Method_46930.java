/** 
 * Publish the results of the progress to notification and  {@link DatapointParcelable}and eventually to  {@link ProcessViewerFragment}
 * @param speed          number of bytes being copied per sec
 * @param isComplete     whether operation completed or ongoing (not supported at the moment)
 * @param move           if the files are to be moved
 */
public final void publishResults(long speed,boolean isComplete,boolean move){
  if (!getProgressHandler().getCancelled()) {
    String fileName=getProgressHandler().getFileName();
    long totalSize=getProgressHandler().getTotalSize();
    long writtenSize=getProgressHandler().getWrittenSize();
    if (!isNotificationTitleSet) {
      getNotificationBuilder().setSubText(getString(getTitle(move)));
      isNotificationTitleSet=true;
    }
    if (ServiceWatcherUtil.state != ServiceWatcherUtil.ServiceWatcherInteractionInterface.STATE_HALTED) {
      String written=Formatter.formatFileSize(this,writtenSize) + "/" + Formatter.formatFileSize(this,totalSize);
      getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_filename_big,fileName);
      getNotificationCustomViewSmall().setTextViewText(R.id.notification_service_textView_filename_small,fileName);
      getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_written_big,written);
      getNotificationCustomViewSmall().setTextViewText(R.id.notification_service_textView_written_small,written);
      getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_transferRate_big,Formatter.formatFileSize(this,speed) + "/s");
      String remainingTime;
      if (speed != 0) {
        remainingTime=Utils.formatTimer(Math.round((totalSize - writtenSize) / speed));
      }
 else {
        remainingTime=getString(R.string.unknown);
      }
      getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_timeRemaining_big,remainingTime);
      getNotificationCustomViewSmall().setProgressBar(R.id.notification_service_progressBar_small,100,Math.round(getProgressHandler().getPercentProgress()),false);
      getNotificationCustomViewBig().setProgressBar(R.id.notification_service_progressBar_big,100,Math.round(getProgressHandler().getPercentProgress()),false);
      getNotificationManager().notify(getNotificationId(),getNotificationBuilder().build());
    }
    if (writtenSize == totalSize || totalSize == 0) {
      if (move && getNotificationId() == NotificationConstants.COPY_ID) {
        getNotificationCustomViewSmall().setProgressBar(R.id.notification_service_progressBar_small,0,0,true);
        getNotificationCustomViewBig().setProgressBar(R.id.notification_service_progressBar_big,0,0,true);
        getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_filename_big,getString(R.string.processing));
        getNotificationCustomViewSmall().setTextViewText(R.id.notification_service_textView_filename_small,getString(R.string.processing));
        getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_timeRemaining_big,getString(R.string.unknown));
        getNotificationCustomViewBig().setTextViewText(R.id.notification_service_textView_transferRate_big,getString(R.string.unknown));
        getNotificationBuilder().setOngoing(false);
        getNotificationBuilder().setAutoCancel(true);
        getNotificationManager().notify(getNotificationId(),getNotificationBuilder().build());
      }
 else {
        publishCompletedResult(getNotificationId());
      }
    }
    DatapointParcelable intent=new DatapointParcelable(fileName,getProgressHandler().getSourceSize(),getProgressHandler().getSourceFilesProcessed(),totalSize,writtenSize,speed,move,isComplete);
    addDatapoint(intent);
  }
 else   publishCompletedResult(getNotificationId());
}
