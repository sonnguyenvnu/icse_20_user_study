public boolean scheduleVideoConvert(MessageObject messageObject,boolean isEmpty){
  if (messageObject == null || messageObject.videoEditedInfo == null) {
    return false;
  }
  if (isEmpty && !videoConvertQueue.isEmpty()) {
    return false;
  }
 else   if (isEmpty) {
    new File(messageObject.messageOwner.attachPath).delete();
  }
  videoConvertQueue.add(messageObject);
  if (videoConvertQueue.size() == 1) {
    startVideoConvertFromQueue();
  }
  return true;
}
