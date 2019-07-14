private void didWriteData(final MessageObject messageObject,final File file,final boolean last,long availableSize,final boolean error){
  final boolean firstWrite=videoConvertFirstWrite;
  if (firstWrite) {
    videoConvertFirstWrite=false;
  }
  AndroidUtilities.runOnUIThread(() -> {
    if (error || last) {
synchronized (videoConvertSync) {
        cancelCurrentVideoConversion=false;
      }
      videoConvertQueue.remove(messageObject);
      startVideoConvertFromQueue();
    }
    if (error) {
      NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.filePreparingFailed,messageObject,file.toString());
    }
 else {
      if (firstWrite) {
        NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.filePreparingStarted,messageObject,file.toString());
      }
      NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.fileNewChunkAvailable,messageObject,file.toString(),availableSize,last ? file.length() : 0);
    }
  }
);
}
