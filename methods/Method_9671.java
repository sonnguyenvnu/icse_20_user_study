private void updateButtonState(RadialProgress2 radial,Object image,DownloadController.FileDownloadProgressListener listener,boolean ifSame,boolean animated){
  Object object;
  if (listener == this) {
    if (selectedPattern != null) {
      object=selectedPattern;
    }
 else {
      object=currentWallpaper;
    }
  }
 else {
    object=image;
  }
  if (object instanceof TLRPC.TL_wallPaper || object instanceof MediaController.SearchImage) {
    if (image == null) {
      if (animated && !progressVisible) {
        animated=false;
      }
    }
    boolean fileExists;
    File path;
    int size;
    String fileName;
    if (object instanceof TLRPC.TL_wallPaper) {
      TLRPC.TL_wallPaper wallPaper=(TLRPC.TL_wallPaper)object;
      fileName=FileLoader.getAttachFileName(wallPaper.document);
      if (TextUtils.isEmpty(fileName)) {
        return;
      }
      path=FileLoader.getPathToAttach(wallPaper.document,true);
      size=wallPaper.document.size;
    }
 else {
      MediaController.SearchImage wallPaper=(MediaController.SearchImage)object;
      if (wallPaper.photo != null) {
        TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(wallPaper.photo.sizes,maxWallpaperSize,true);
        path=FileLoader.getPathToAttach(photoSize,true);
        fileName=FileLoader.getAttachFileName(photoSize);
        size=photoSize.size;
      }
 else {
        path=ImageLoader.getHttpFilePath(wallPaper.imageUrl,"jpg");
        fileName=path.getName();
        size=wallPaper.size;
      }
      if (TextUtils.isEmpty(fileName)) {
        return;
      }
    }
    if (fileExists=path.exists()) {
      DownloadController.getInstance(currentAccount).removeLoadingFileObserver(listener);
      radial.setProgress(1,animated);
      radial.setIcon(image == null ? MediaActionDrawable.ICON_NONE : MediaActionDrawable.ICON_CHECK,ifSame,animated);
      if (image == null) {
        backgroundImage.invalidate();
        if (size != 0) {
          actionBar.setSubtitle(AndroidUtilities.formatFileSize(size));
        }
 else {
          actionBar.setSubtitle(null);
        }
      }
    }
 else {
      DownloadController.getInstance(currentAccount).addLoadingFileObserver(fileName,null,listener);
      boolean isLoading=FileLoader.getInstance(currentAccount).isLoadingFile(fileName);
      Float progress=ImageLoader.getInstance().getFileProgress(fileName);
      if (progress != null) {
        radial.setProgress(progress,animated);
      }
 else {
        radial.setProgress(0,animated);
      }
      radial.setIcon(MediaActionDrawable.ICON_EMPTY,ifSame,animated);
      if (image == null) {
        actionBar.setSubtitle(LocaleController.getString("LoadingFullImage",R.string.LoadingFullImage));
        backgroundImage.invalidate();
      }
    }
    if (image == null) {
      if (selectedPattern == null) {
        buttonsContainer.setAlpha(fileExists ? 1.0f : 0.5f);
      }
      bottomOverlayChat.setEnabled(fileExists);
      bottomOverlayChatText.setAlpha(fileExists ? 1.0f : 0.5f);
    }
  }
 else {
    radial.setIcon(listener == this ? MediaActionDrawable.ICON_NONE : MediaActionDrawable.ICON_CHECK,ifSame,animated);
  }
}
