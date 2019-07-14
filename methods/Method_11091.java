public void setColor(int color){
  Bitmap heart=createHeart(color);
  setImageDrawable(new BitmapDrawable(getResources(),heart));
}
