private static float getFingerSpacing(MotionEvent event){
  float x=event.getX(0) - event.getX(1);
  float y=event.getY(0) - event.getY(1);
  Log.e("Camera","getFingerSpacing ????? = " + (float)Math.sqrt(x * x + y * y));
  return (float)Math.sqrt(x * x + y * y);
}
