private void drawStateDrawable(Canvas canvas){
  if (drawable != null) {
    if (drawableAsBackground) {
      drawDrawableBackground(canvas);
    }
 else     if (isShowState) {
      getDrawableBounds();
      drawable.setBounds((int)drawableBounds[0],(int)drawableBounds[1],(int)drawableBounds[2],(int)drawableBounds[3]);
      if (drawableTint != NO_COLOR) {
        drawable.setColorFilter(drawableTint,PorterDuff.Mode.SRC_IN);
      }
      if (drawableRotate != NO_ROTATE) {
        canvas.save();
        canvas.rotate(drawableRotate,drawableBounds[0] + (drawableBounds[2] - drawableBounds[0]) / 2,drawableBounds[1] + (drawableBounds[3] - drawableBounds[1]) / 2);
        drawable.draw(canvas);
        canvas.restore();
      }
 else {
        drawable.draw(canvas);
      }
    }
  }
  if (drawable2 != null && isShowState2) {
    getDrawable2Bounds();
    drawable2.setBounds((int)drawable2Bounds[0],(int)drawable2Bounds[1],(int)drawable2Bounds[2],(int)drawable2Bounds[3]);
    if (drawable2Tint != NO_COLOR) {
      drawable2.setColorFilter(drawable2Tint,PorterDuff.Mode.SRC_IN);
    }
    if (drawable2Rotate != NO_ROTATE) {
      canvas.save();
      canvas.rotate(drawable2Rotate,drawable2Bounds[0] + (drawable2Bounds[2] - drawable2Bounds[0]) / 2,drawable2Bounds[1] + (drawable2Bounds[3] - drawable2Bounds[1]) / 2);
      drawable2.draw(canvas);
      canvas.restore();
    }
 else {
      drawable2.draw(canvas);
    }
  }
}
