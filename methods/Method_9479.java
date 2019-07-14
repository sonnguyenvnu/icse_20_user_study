private void applyCurrentEditMode(){
  Bitmap bitmap=null;
  ArrayList<TLRPC.InputDocument> stickers=null;
  MediaController.SavedFilterState savedFilterState=null;
  boolean removeSavedState=false;
  if (currentEditMode == 1 || currentEditMode == 0 && sendPhotoType == SELECT_TYPE_AVATAR) {
    bitmap=photoCropView.getBitmap();
    removeSavedState=true;
  }
 else   if (currentEditMode == 2) {
    bitmap=photoFilterView.getBitmap();
    savedFilterState=photoFilterView.getSavedFilterState();
  }
 else   if (currentEditMode == 3) {
    bitmap=photoPaintView.getBitmap();
    stickers=photoPaintView.getMasks();
    removeSavedState=true;
  }
  if (bitmap != null) {
    TLRPC.PhotoSize size=ImageLoader.scaleAndSaveImage(bitmap,AndroidUtilities.getPhotoSize(),AndroidUtilities.getPhotoSize(),80,false,101,101);
    if (size != null) {
      Object object=imagesArrLocals.get(currentIndex);
      if (object instanceof MediaController.PhotoEntry) {
        MediaController.PhotoEntry entry=(MediaController.PhotoEntry)object;
        entry.imagePath=FileLoader.getPathToAttach(size,true).toString();
        size=ImageLoader.scaleAndSaveImage(bitmap,AndroidUtilities.dp(120),AndroidUtilities.dp(120),70,false,101,101);
        if (size != null) {
          entry.thumbPath=FileLoader.getPathToAttach(size,true).toString();
        }
        if (stickers != null) {
          entry.stickers.addAll(stickers);
        }
        if (currentEditMode == 1) {
          cropItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isCropped=true;
        }
 else         if (currentEditMode == 2) {
          tuneItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isFiltered=true;
        }
 else         if (currentEditMode == 3) {
          paintItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isPainted=true;
        }
        if (savedFilterState != null) {
          entry.savedFilterState=savedFilterState;
        }
 else         if (removeSavedState) {
          entry.savedFilterState=null;
        }
      }
 else       if (object instanceof MediaController.SearchImage) {
        MediaController.SearchImage entry=(MediaController.SearchImage)object;
        entry.imagePath=FileLoader.getPathToAttach(size,true).toString();
        size=ImageLoader.scaleAndSaveImage(bitmap,AndroidUtilities.dp(120),AndroidUtilities.dp(120),70,false,101,101);
        if (size != null) {
          entry.thumbPath=FileLoader.getPathToAttach(size,true).toString();
        }
        if (stickers != null) {
          entry.stickers.addAll(stickers);
        }
        if (currentEditMode == 1) {
          cropItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isCropped=true;
        }
 else         if (currentEditMode == 2) {
          tuneItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isFiltered=true;
        }
 else         if (currentEditMode == 3) {
          paintItem.setColorFilter(new PorterDuffColorFilter(0xff3dadee,PorterDuff.Mode.MULTIPLY));
          entry.isPainted=true;
        }
        if (savedFilterState != null) {
          entry.savedFilterState=savedFilterState;
        }
 else         if (removeSavedState) {
          entry.savedFilterState=null;
        }
      }
      if ((sendPhotoType == 0 || sendPhotoType == 4) && placeProvider != null) {
        placeProvider.updatePhotoAtIndex(currentIndex);
        if (!placeProvider.isPhotoChecked(currentIndex)) {
          setPhotoChecked();
        }
      }
      if (currentEditMode == 1) {
        float scaleX=photoCropView.getRectSizeX() / (float)getContainerViewWidth();
        float scaleY=photoCropView.getRectSizeY() / (float)getContainerViewHeight();
        scale=scaleX > scaleY ? scaleX : scaleY;
        translationX=photoCropView.getRectX() + photoCropView.getRectSizeX() / 2 - getContainerViewWidth() / 2;
        translationY=photoCropView.getRectY() + photoCropView.getRectSizeY() / 2 - getContainerViewHeight() / 2;
        zoomAnimation=true;
        applying=true;
        photoCropView.onDisappear();
      }
      centerImage.setParentView(null);
      centerImage.setOrientation(0,true);
      ignoreDidSetImage=true;
      centerImage.setImageBitmap(bitmap);
      ignoreDidSetImage=false;
      centerImage.setParentView(containerView);
      if (sendPhotoType == SELECT_TYPE_AVATAR) {
        setCropBitmap();
      }
    }
  }
}
