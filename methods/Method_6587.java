public AnimatedFileDrawable getAnimation(){
  AnimatedFileDrawable animatedFileDrawable;
  if (currentMediaDrawable instanceof AnimatedFileDrawable) {
    return (AnimatedFileDrawable)currentMediaDrawable;
  }
 else   if (currentImageDrawable instanceof AnimatedFileDrawable) {
    return (AnimatedFileDrawable)currentImageDrawable;
  }
 else   if (currentThumbDrawable instanceof AnimatedFileDrawable) {
    return (AnimatedFileDrawable)currentThumbDrawable;
  }
 else   if (staticThumbDrawable instanceof AnimatedFileDrawable) {
    return (AnimatedFileDrawable)staticThumbDrawable;
  }
  return null;
}
