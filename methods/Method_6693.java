public void cancelVideoConvert(MessageObject messageObject){
  if (messageObject == null) {
synchronized (videoConvertSync) {
      cancelCurrentVideoConversion=true;
    }
  }
 else {
    if (!videoConvertQueue.isEmpty()) {
      for (int a=0; a < videoConvertQueue.size(); a++) {
        MessageObject object=videoConvertQueue.get(a);
        if (object.getId() == messageObject.getId() && object.currentAccount == messageObject.currentAccount) {
          if (a == 0) {
synchronized (videoConvertSync) {
              cancelCurrentVideoConversion=true;
            }
          }
 else {
            videoConvertQueue.remove(a);
          }
          break;
        }
      }
    }
  }
}
