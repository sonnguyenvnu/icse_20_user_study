@Override public void init(int width,int height){
  if (this.width != width || this.height != height) {
    this.width=width;
    this.height=height;
    bitmapBoard=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    canvas=new Canvas(bitmapBoard);
  }
}
