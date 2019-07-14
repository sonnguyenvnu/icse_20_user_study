public void setImageBitmap(Drawable bitmap){
  ImageLoader.getInstance().cancelLoadingForImageReceiver(this,true);
  if (crossfadeWithOldImage) {
    if (currentImageDrawable != null) {
      recycleBitmap(null,TYPE_THUMB);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(null,TYPE_MEDIA);
      crossfadeShader=imageShader;
      crossfadeImage=currentImageDrawable;
      crossfadeKey=currentImageKey;
      crossfadingWithThumb=true;
    }
 else     if (currentThumbDrawable != null) {
      recycleBitmap(null,TYPE_IMAGE);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(null,TYPE_MEDIA);
      crossfadeShader=thumbShader;
      crossfadeImage=currentThumbDrawable;
      crossfadeKey=currentThumbKey;
      crossfadingWithThumb=true;
    }
 else     if (staticThumbDrawable != null) {
      recycleBitmap(null,TYPE_IMAGE);
      recycleBitmap(null,TYPE_THUMB);
      recycleBitmap(null,TYPE_CROSSFDADE);
      recycleBitmap(null,TYPE_MEDIA);
      crossfadeShader=thumbShader;
      crossfadeImage=staticThumbDrawable;
      crossfadingWithThumb=true;
      crossfadeKey=null;
    }
 else {
      for (int a=0; a < 4; a++) {
        recycleBitmap(null,a);
      }
      crossfadeShader=null;
    }
  }
 else {
    for (int a=0; a < 4; a++) {
      recycleBitmap(null,a);
    }
  }
  if (staticThumbDrawable instanceof RecyclableDrawable) {
    RecyclableDrawable drawable=(RecyclableDrawable)staticThumbDrawable;
    drawable.recycle();
  }
  if (bitmap instanceof AnimatedFileDrawable) {
    AnimatedFileDrawable fileDrawable=(AnimatedFileDrawable)bitmap;
    fileDrawable.setParentView(parentView);
    fileDrawable.setUseSharedQueue(useSharedAnimationQueue);
    if (allowStartAnimation) {
      fileDrawable.start();
    }
    fileDrawable.setAllowDecodeSingleFrame(allowDecodeSingleFrame);
  }
  staticThumbDrawable=bitmap;
  if (roundRadius != 0 && bitmap instanceof BitmapDrawable) {
    if (bitmap instanceof AnimatedFileDrawable) {
      ((AnimatedFileDrawable)bitmap).setRoundRadius(roundRadius);
    }
 else {
      Bitmap object=((BitmapDrawable)bitmap).getBitmap();
      thumbShader=new BitmapShader(object,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
    }
  }
 else {
    thumbShader=null;
  }
  currentMediaLocation=null;
  currentMediaFilter=null;
  currentMediaDrawable=null;
  currentMediaKey=null;
  mediaShader=null;
  currentImageLocation=null;
  currentImageFilter=null;
  currentImageDrawable=null;
  currentImageKey=null;
  imageShader=null;
  currentThumbLocation=null;
  currentThumbFilter=null;
  currentThumbKey=null;
  currentKeyQuality=false;
  currentExt=null;
  currentSize=0;
  currentCacheType=0;
  currentAlpha=1;
  if (setImageBackup != null) {
    setImageBackup.imageLocation=null;
    setImageBackup.thumbLocation=null;
    setImageBackup.mediaLocation=null;
    setImageBackup.thumb=null;
  }
  if (delegate != null) {
    delegate.didSetImage(this,currentThumbDrawable != null || staticThumbDrawable != null,true);
  }
  if (parentView != null) {
    if (invalidateAll) {
      parentView.invalidate();
    }
 else {
      parentView.invalidate(imageX,imageY,imageX + imageW,imageY + imageH);
    }
  }
  if (forceCrossfade && crossfadeWithOldImage && crossfadeImage != null) {
    currentAlpha=0.0f;
    lastUpdateAlphaTime=System.currentTimeMillis();
    crossfadeWithThumb=currentThumbDrawable != null || staticThumbDrawable != null;
  }
}
