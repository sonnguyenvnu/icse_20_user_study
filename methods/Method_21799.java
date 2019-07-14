private void calculateBounds(int width,int height){
  final int radius=Math.min(height,width);
  final int offset=Math.abs(height - width) / 2;
  final int circleOffset=Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP ? offset / 2 : offset;
  if (width >= height) {
    tempRect.set(offset,0,radius + offset,height);
    circleDrawableRect.set(circleOffset,0,radius + circleOffset,height);
  }
 else {
    tempRect.set(0,offset,width,radius + offset);
    circleDrawableRect.set(0,circleOffset,width,radius + circleOffset);
  }
}
