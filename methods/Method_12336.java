private void updateTextureViewSize(final GifInfoHandle gifInfoHandle){
  final Matrix transform=new Matrix();
  final float viewWidth=getWidth();
  final float viewHeight=getHeight();
  final float scaleRef;
  final float scaleX=gifInfoHandle.getWidth() / viewWidth;
  final float scaleY=gifInfoHandle.getHeight() / viewHeight;
  RectF src=new RectF(0,0,gifInfoHandle.getWidth(),gifInfoHandle.getHeight());
  RectF dst=new RectF(0,0,viewWidth,viewHeight);
switch (mScaleType) {
case CENTER:
    transform.setScale(scaleX,scaleY,viewWidth / 2,viewHeight / 2);
  break;
case CENTER_CROP:
scaleRef=1 / Math.min(scaleX,scaleY);
transform.setScale(scaleRef * scaleX,scaleRef * scaleY,viewWidth / 2,viewHeight / 2);
break;
case CENTER_INSIDE:
if (gifInfoHandle.getWidth() <= viewWidth && gifInfoHandle.getHeight() <= viewHeight) {
scaleRef=1.0f;
}
 else {
scaleRef=Math.min(1 / scaleX,1 / scaleY);
}
transform.setScale(scaleRef * scaleX,scaleRef * scaleY,viewWidth / 2,viewHeight / 2);
break;
case FIT_CENTER:
transform.setRectToRect(src,dst,Matrix.ScaleToFit.CENTER);
transform.preScale(scaleX,scaleY);
break;
case FIT_END:
transform.setRectToRect(src,dst,Matrix.ScaleToFit.END);
transform.preScale(scaleX,scaleY);
break;
case FIT_START:
transform.setRectToRect(src,dst,Matrix.ScaleToFit.START);
transform.preScale(scaleX,scaleY);
break;
case FIT_XY:
return;
case MATRIX:
transform.set(mTransform);
transform.preScale(scaleX,scaleY);
break;
}
super.setTransform(transform);
}
