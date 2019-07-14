public int getAnimatedOrientation(){
  AnimatedFileDrawable animation=getAnimation();
  return animation != null ? animation.getOrientation() : 0;
}
