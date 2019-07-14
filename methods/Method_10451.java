private void drawNinepath(Canvas canvas,int resId,Rect rect){
  Bitmap bmp=BitmapFactory.decodeResource(getResources(),resId);
  NinePatch patch=new NinePatch(bmp,bmp.getNinePatchChunk(),null);
  patch.draw(canvas,rect);
}
