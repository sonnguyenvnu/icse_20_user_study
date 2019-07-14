private void initialise(){
  float density=getResources().getDisplayMetrics().densityDpi;
  pin=BitmapFactory.decodeResource(this.getResources(),R.drawable.pushpin_blue);
  float w=(density / 420f) * pin.getWidth();
  float h=(density / 420f) * pin.getHeight();
  pin=Bitmap.createScaledBitmap(pin,(int)w,(int)h,true);
}
