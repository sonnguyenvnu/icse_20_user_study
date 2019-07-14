private void sendSelectedPhotos(HashMap<Object,Object> photos,ArrayList<Object> order){
  if (photos.isEmpty() || delegate == null || sendPressed) {
    return;
  }
  sendPressed=true;
  boolean gifChanged=false;
  boolean webChange=false;
  ArrayList<SendMessagesHelper.SendingMediaInfo> media=new ArrayList<>();
  for (int a=0; a < order.size(); a++) {
    Object object=photos.get(order.get(a));
    SendMessagesHelper.SendingMediaInfo info=new SendMessagesHelper.SendingMediaInfo();
    media.add(info);
    if (object instanceof MediaController.PhotoEntry) {
      MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)object;
      if (photoEntry.isVideo) {
        info.path=photoEntry.path;
        info.videoEditedInfo=photoEntry.editedInfo;
      }
 else       if (photoEntry.imagePath != null) {
        info.path=photoEntry.imagePath;
      }
 else       if (photoEntry.path != null) {
        info.path=photoEntry.path;
      }
      info.isVideo=photoEntry.isVideo;
      info.caption=photoEntry.caption != null ? photoEntry.caption.toString() : null;
      info.entities=photoEntry.entities;
      info.masks=!photoEntry.stickers.isEmpty() ? new ArrayList<>(photoEntry.stickers) : null;
      info.ttl=photoEntry.ttl;
    }
 else     if (object instanceof MediaController.SearchImage) {
      MediaController.SearchImage searchImage=(MediaController.SearchImage)object;
      if (searchImage.imagePath != null) {
        info.path=searchImage.imagePath;
      }
 else {
        info.searchImage=searchImage;
      }
      info.caption=searchImage.caption != null ? searchImage.caption.toString() : null;
      info.entities=searchImage.entities;
      info.masks=!searchImage.stickers.isEmpty() ? new ArrayList<>(searchImage.stickers) : null;
      info.ttl=searchImage.ttl;
      searchImage.date=(int)(System.currentTimeMillis() / 1000);
      if (searchImage.type == 0) {
        webChange=true;
        MediaController.SearchImage recentImage=recentImagesWebKeys.get(searchImage.id);
        if (recentImage != null) {
          recentWebImages.remove(recentImage);
          recentWebImages.add(0,recentImage);
        }
 else {
          recentWebImages.add(0,searchImage);
        }
      }
 else       if (searchImage.type == 1) {
        gifChanged=true;
        MediaController.SearchImage recentImage=recentImagesGifKeys.get(searchImage.id);
        if (recentImage != null) {
          recentGifImages.remove(recentImage);
          recentGifImages.add(0,recentImage);
        }
 else {
          recentGifImages.add(0,searchImage);
        }
      }
    }
  }
  if (webChange) {
    MessagesStorage.getInstance(currentAccount).putWebRecent(recentWebImages);
  }
  if (gifChanged) {
    MessagesStorage.getInstance(currentAccount).putWebRecent(recentGifImages);
  }
  delegate.didSelectPhotos(media);
}
