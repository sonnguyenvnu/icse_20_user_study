public void setImage(ImageLocation mediaLocation,String mediaFilter,ImageLocation imageLocation,String imageFilter,ImageLocation thumbLocation,String thumbFilter,Drawable thumb,int size,String ext,Object parentObject,int cacheType){
  if (setImageBackup != null) {
    setImageBackup.imageLocation=null;
    setImageBackup.thumbLocation=null;
    setImageBackup.mediaLocation=null;
    setImageBackup.thumb=null;
  }
  if (imageLocation == null && thumbLocation == null && mediaLocation == null) {
    for (int a=0; a < 4; a++) {
      recycleBitmap(null,a);
    }
    currentImageLocation=null;
    currentImageFilter=null;
    currentImageKey=null;
    currentMediaLocation=null;
    currentMediaFilter=null;
    currentMediaKey=null;
    currentThumbLocation=null;
    currentThumbFilter=null;
    currentThumbKey=null;
    currentMediaDrawable=null;
    mediaShader=null;
    currentImageDrawable=null;
    imageShader=null;
    thumbShader=null;
    crossfadeShader=null;
    currentExt=ext;
    currentParentObject=null;
    currentCacheType=0;
    staticThumbDrawable=thumb;
    currentAlpha=1.0f;
    currentSize=0;
    ImageLoader.getInstance().cancelLoadingForImageReceiver(this,true);
    if (parentView != null) {
      if (invalidateAll) {
        parentView.invalidate();
      }
 else {
        parentView.invalidate(imageX,imageY,imageX + imageW,imageY + imageH);
      }
    }
    if (delegate != null) {
      delegate.didSetImage(this,currentImageDrawable != null || currentThumbDrawable != null || staticThumbDrawable != null || currentMediaDrawable != null,currentImageDrawable == null && currentMediaDrawable == null);
    }
    return;
  }
  String imageKey=imageLocation != null ? imageLocation.getKey(parentObject,null) : null;
  if (imageKey == null && imageLocation != null) {
    imageLocation=null;
  }
  currentKeyQuality=false;
  if (imageKey == null && needsQualityThumb && (parentObject instanceof MessageObject || qulityThumbDocument != null)) {
    TLRPC.Document document=qulityThumbDocument != null ? qulityThumbDocument : ((MessageObject)parentObject).getDocument();
    if (document != null && document.dc_id != 0 && document.id != 0) {
      imageKey="q_" + document.dc_id + "_" + document.id;
      currentKeyQuality=true;
    }
  }
  if (imageKey != null && imageFilter != null) {
    imageKey+="@" + imageFilter;
  }
  String mediaKey=mediaLocation != null ? mediaLocation.getKey(parentObject,null) : null;
  if (mediaKey == null && mediaLocation != null) {
    mediaLocation=null;
  }
  if (mediaKey != null && mediaFilter != null) {
    mediaKey+="@" + mediaFilter;
  }
  if (mediaKey == null && currentImageKey != null && currentImageKey.equals(imageKey) || currentMediaKey != null && currentMediaKey.equals(mediaKey)) {
    if (delegate != null) {
      delegate.didSetImage(this,currentImageDrawable != null || currentThumbDrawable != null || staticThumbDrawable != null || currentMediaDrawable != null,currentImageDrawable == null && currentMediaDrawable == null);
    }
    if (!canceledLoading && !forcePreview) {
      return;
    }
  }
  ImageLocation strippedLoc;
  if (strippedLocation != null) {
    strippedLoc=strippedLocation;
  }
 else {
    strippedLoc=mediaLocation != null ? mediaLocation : imageLocation;
  }
  String thumbKey=thumbLocation != null ? thumbLocation.getKey(parentObject,strippedLoc) : null;
  if (thumbKey != null && thumbFilter != null) {
    thumbKey+="@" + thumbFilter;
  }
  if (crossfadeWithOldImage) {
    if (currentImageDrawable != null) {
      recycleBitmap(thumbKey,TYPE_THUMB);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(mediaKey,TYPE_MEDIA);
      crossfadeShader=imageShader;
      crossfadeImage=currentImageDrawable;
      crossfadeKey=currentImageKey;
      crossfadingWithThumb=false;
      currentImageDrawable=null;
      currentImageKey=null;
    }
 else     if (currentThumbDrawable != null) {
      recycleBitmap(imageKey,TYPE_IMAGE);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(mediaKey,TYPE_MEDIA);
      crossfadeShader=thumbShader;
      crossfadeImage=currentThumbDrawable;
      crossfadeKey=currentThumbKey;
      crossfadingWithThumb=false;
      currentThumbDrawable=null;
      currentThumbKey=null;
    }
 else     if (staticThumbDrawable != null) {
      recycleBitmap(imageKey,TYPE_IMAGE);
      recycleBitmap(thumbKey,TYPE_THUMB);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(mediaKey,TYPE_MEDIA);
      crossfadeShader=thumbShader;
      crossfadeImage=staticThumbDrawable;
      crossfadingWithThumb=false;
      crossfadeKey=null;
      currentThumbDrawable=null;
      currentThumbKey=null;
    }
 else {
      recycleBitmap(imageKey,TYPE_IMAGE);
      recycleBitmap(thumbKey,TYPE_THUMB);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(mediaKey,TYPE_MEDIA);
      crossfadeShader=null;
    }
  }
 else {
    recycleBitmap(imageKey,TYPE_IMAGE);
    recycleBitmap(thumbKey,TYPE_THUMB);
    recycleBitmap(null,TYPE_CROSSFDADE);
    recycleBitmap(mediaKey,TYPE_MEDIA);
    crossfadeShader=null;
  }
  currentImageLocation=imageLocation;
  currentImageFilter=imageFilter;
  currentImageKey=imageKey;
  currentMediaLocation=mediaLocation;
  currentMediaFilter=mediaFilter;
  currentMediaKey=mediaKey;
  currentThumbLocation=thumbLocation;
  currentThumbFilter=thumbFilter;
  currentThumbKey=thumbKey;
  currentParentObject=parentObject;
  currentExt=ext;
  currentSize=size;
  currentCacheType=cacheType;
  staticThumbDrawable=thumb;
  imageShader=null;
  thumbShader=null;
  mediaShader=null;
  currentAlpha=1.0f;
  if (delegate != null) {
    delegate.didSetImage(this,currentImageDrawable != null || currentThumbDrawable != null || staticThumbDrawable != null || currentMediaDrawable != null,currentImageDrawable == null && currentMediaDrawable == null);
  }
  ImageLoader.getInstance().loadImageForImageReceiver(this);
  if (parentView != null) {
    if (invalidateAll) {
      parentView.invalidate();
    }
 else {
      parentView.invalidate(imageX,imageY,imageX + imageW,imageY + imageH);
    }
  }
}
