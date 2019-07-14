protected void applyAnimatedFraction(@NonNull PointF holder,float fraction){
  holder.x=interpolate(fraction,mStartLeft,mEndLeft);
  holder.y=interpolate(fraction,mStartTop,mEndTop);
}
