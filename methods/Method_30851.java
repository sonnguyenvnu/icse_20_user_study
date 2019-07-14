private Bitmap getBitmapFromDrawable(Drawable drawable){
  if (drawable == null) {
    return null;
  }
  if (drawable instanceof BitmapDrawable) {
    return ((BitmapDrawable)drawable).getBitmap();
  }
  try {
    Bitmap bitmap;
    if (drawable instanceof ColorDrawable) {
      bitmap=Bitmap.createBitmap(COLOR_DRAWABLE_DIMENSION,COLOR_DRAWABLE_DIMENSION,BITMAP_CONFIG);
    }
 else {
      bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),BITMAP_CONFIG);
    }
    Canvas canvas=new Canvas(bitmap);
    drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
    drawable.draw(canvas);
    return bitmap;
  }
 catch (  Exception e) {
    e.printStackTrace();
    return null;
  }
}
