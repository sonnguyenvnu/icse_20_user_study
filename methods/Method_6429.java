private void checkDownloadFinished(String fileName,int state){
  DownloadObject downloadObject=downloadQueueKeys.get(fileName);
  if (downloadObject != null) {
    downloadQueueKeys.remove(fileName);
    if (state == 0 || state == 2) {
      MessagesStorage.getInstance(currentAccount).removeFromDownloadQueue(downloadObject.id,downloadObject.type,false);
    }
    if (downloadObject.type == AUTODOWNLOAD_TYPE_PHOTO) {
      photoDownloadQueue.remove(downloadObject);
      if (photoDownloadQueue.isEmpty()) {
        newDownloadObjectsAvailable(AUTODOWNLOAD_TYPE_PHOTO);
      }
    }
 else     if (downloadObject.type == AUTODOWNLOAD_TYPE_AUDIO) {
      audioDownloadQueue.remove(downloadObject);
      if (audioDownloadQueue.isEmpty()) {
        newDownloadObjectsAvailable(AUTODOWNLOAD_TYPE_AUDIO);
      }
    }
 else     if (downloadObject.type == AUTODOWNLOAD_TYPE_VIDEO) {
      videoDownloadQueue.remove(downloadObject);
      if (videoDownloadQueue.isEmpty()) {
        newDownloadObjectsAvailable(AUTODOWNLOAD_TYPE_VIDEO);
      }
    }
 else     if (downloadObject.type == AUTODOWNLOAD_TYPE_DOCUMENT) {
      documentDownloadQueue.remove(downloadObject);
      if (documentDownloadQueue.isEmpty()) {
        newDownloadObjectsAvailable(AUTODOWNLOAD_TYPE_DOCUMENT);
      }
    }
  }
}
