private void updateBlurredPhotos(final ImageReceiver.BitmapHolder src){
  new Thread(new Runnable(){
    @Override public void run(){
      try {
        Bitmap blur1=Bitmap.createBitmap(150,150,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(blur1);
        canvas.drawBitmap(src.bitmap,null,new Rect(0,0,150,150),new Paint(Paint.FILTER_BITMAP_FLAG));
        Utilities.blurBitmap(blur1,3,0,blur1.getWidth(),blur1.getHeight(),blur1.getRowBytes());
        final Palette palette=Palette.from(src.bitmap).generate();
        Paint paint=new Paint();
        paint.setColor((palette.getDarkMutedColor(0xFF547499) & 0x00FFFFFF) | 0x44000000);
        canvas.drawColor(0x26000000);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),paint);
        Bitmap blur2=Bitmap.createBitmap(50,50,Bitmap.Config.ARGB_8888);
        canvas=new Canvas(blur2);
        canvas.drawBitmap(src.bitmap,null,new Rect(0,0,50,50),new Paint(Paint.FILTER_BITMAP_FLAG));
        Utilities.blurBitmap(blur2,3,0,blur2.getWidth(),blur2.getHeight(),blur2.getRowBytes());
        paint.setAlpha(0x66);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),paint);
        blurredPhoto1=blur1;
        blurredPhoto2=blur2;
        runOnUiThread(new Runnable(){
          @Override public void run(){
            blurOverlayView1.setImageBitmap(blurredPhoto1);
            blurOverlayView2.setImageBitmap(blurredPhoto2);
            src.release();
          }
        }
);
      }
 catch (      Throwable ignore) {
      }
    }
  }
).start();
}
