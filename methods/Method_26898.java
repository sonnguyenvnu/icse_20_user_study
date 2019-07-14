@SuppressLint("RtlHardcoded") private int distance(@NonNull View sceneRoot,int viewX,int viewY,int epicenterX,int epicenterY,int left,int top,int right,int bottom){
  final int side;
  if (mSide == Gravity.START) {
    final boolean isRtl=ViewUtils.isRtl(sceneRoot);
    side=isRtl ? Gravity.RIGHT : Gravity.LEFT;
  }
 else   if (mSide == Gravity.END) {
    final boolean isRtl=ViewUtils.isRtl(sceneRoot);
    side=isRtl ? Gravity.LEFT : Gravity.RIGHT;
  }
 else {
    side=mSide;
  }
  int distance=0;
switch (side) {
case Gravity.LEFT:
    distance=right - viewX + Math.abs(epicenterY - viewY);
  break;
case Gravity.TOP:
distance=bottom - viewY + Math.abs(epicenterX - viewX);
break;
case Gravity.RIGHT:
distance=viewX - left + Math.abs(epicenterY - viewY);
break;
case Gravity.BOTTOM:
distance=viewY - top + Math.abs(epicenterX - viewX);
break;
}
return distance;
}
