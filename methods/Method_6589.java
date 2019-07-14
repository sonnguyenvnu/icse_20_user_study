protected boolean setImageBitmapByKey(Drawable drawable,String key,int type,boolean memCache){
  if (drawable == null || key == null) {
    return false;
  }
  if (type == TYPE_IMAGE) {
    if (!key.equals(currentImageKey)) {
      return false;
    }
    if (!(drawable instanceof AnimatedFileDrawable) && !(drawable instanceof LottieDrawable)) {
      ImageLoader.getInstance().incrementUseCount(currentImageKey);
    }
    currentImageDrawable=drawable;
    if (drawable instanceof ExtendedBitmapDrawable) {
      imageOrientation=((ExtendedBitmapDrawable)drawable).getOrientation();
    }
    if (roundRadius != 0 && drawable instanceof BitmapDrawable) {
      if (drawable instanceof AnimatedFileDrawable) {
        AnimatedFileDrawable animatedFileDrawable=(AnimatedFileDrawable)drawable;
        animatedFileDrawable.setRoundRadius(roundRadius);
      }
 else {
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
        imageShader=new BitmapShader(bitmapDrawable.getBitmap(),Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
      }
    }
 else {
      imageShader=null;
    }
    if (!memCache && !forcePreview || forceCrossfade) {
      boolean allowCorssfade=true;
      if (currentMediaDrawable instanceof AnimatedFileDrawable && ((AnimatedFileDrawable)currentMediaDrawable).hasBitmap()) {
        allowCorssfade=false;
      }
      if (allowCorssfade && (currentThumbDrawable == null && staticThumbDrawable == null || currentAlpha == 1.0f || forceCrossfade)) {
        currentAlpha=0.0f;
        lastUpdateAlphaTime=System.currentTimeMillis();
        crossfadeWithThumb=crossfadeImage != null || currentThumbDrawable != null || staticThumbDrawable != null;
      }
    }
 else {
      currentAlpha=1.0f;
    }
  }
 else   if (type == TYPE_MEDIA) {
    if (!key.equals(currentMediaKey)) {
      return false;
    }
    if (!(drawable instanceof AnimatedFileDrawable) && !(drawable instanceof LottieDrawable)) {
      ImageLoader.getInstance().incrementUseCount(currentMediaKey);
    }
    currentMediaDrawable=drawable;
    if (roundRadius != 0 && drawable instanceof BitmapDrawable) {
      if (drawable instanceof AnimatedFileDrawable) {
        AnimatedFileDrawable animatedFileDrawable=(AnimatedFileDrawable)drawable;
        animatedFileDrawable.setRoundRadius(roundRadius);
      }
 else {
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
        mediaShader=new BitmapShader(bitmapDrawable.getBitmap(),Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
      }
    }
 else {
      mediaShader=null;
    }
    if (currentImageDrawable == null) {
      if (!memCache && !forcePreview || forceCrossfade) {
        if (currentThumbDrawable == null && staticThumbDrawable == null || currentAlpha == 1.0f || forceCrossfade) {
          currentAlpha=0.0f;
          lastUpdateAlphaTime=System.currentTimeMillis();
          crossfadeWithThumb=crossfadeImage != null || currentThumbDrawable != null || staticThumbDrawable != null;
        }
      }
 else {
        currentAlpha=1.0f;
      }
    }
  }
 else   if (type == TYPE_THUMB) {
    if (currentThumbDrawable != null) {
      return false;
    }
    if (!forcePreview) {
      AnimatedFileDrawable animation=getAnimation();
      if (animation != null && animation.hasBitmap()) {
        return false;
      }
      if (currentImageDrawable != null && !(currentImageDrawable instanceof AnimatedFileDrawable) || currentMediaDrawable != null && !(currentMediaDrawable instanceof AnimatedFileDrawable)) {
        return false;
      }
    }
    if (!key.equals(currentThumbKey)) {
      return false;
    }
    ImageLoader.getInstance().incrementUseCount(currentThumbKey);
    currentThumbDrawable=drawable;
    if (drawable instanceof ExtendedBitmapDrawable) {
      thumbOrientation=((ExtendedBitmapDrawable)drawable).getOrientation();
    }
    if (roundRadius != 0 && drawable instanceof BitmapDrawable) {
      if (drawable instanceof AnimatedFileDrawable) {
        AnimatedFileDrawable animatedFileDrawable=(AnimatedFileDrawable)drawable;
        animatedFileDrawable.setRoundRadius(roundRadius);
      }
 else {
        BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
        thumbShader=new BitmapShader(bitmapDrawable.getBitmap(),Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
      }
    }
 else {
      thumbShader=null;
    }
    if (!memCache && crossfadeAlpha != 2) {
      if (currentParentObject instanceof MessageObject && ((MessageObject)currentParentObject).isRoundVideo() && ((MessageObject)currentParentObject).isSending()) {
        currentAlpha=1.0f;
      }
 else {
        currentAlpha=0.0f;
        lastUpdateAlphaTime=System.currentTimeMillis();
        crossfadeWithThumb=staticThumbDrawable != null && currentImageKey == null && currentMediaKey == null;
      }
    }
 else {
      currentAlpha=1.0f;
    }
  }
  if (drawable instanceof AnimatedFileDrawable) {
    AnimatedFileDrawable fileDrawable=(AnimatedFileDrawable)drawable;
    fileDrawable.setParentView(parentView);
    fileDrawable.setUseSharedQueue(useSharedAnimationQueue);
    if (allowStartAnimation) {
      fileDrawable.start();
    }
    fileDrawable.setAllowDecodeSingleFrame(allowDecodeSingleFrame);
  }
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
  return true;
}
