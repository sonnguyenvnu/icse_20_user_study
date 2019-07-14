public void clearImage(){
  for (int a=0; a < 4; a++) {
    recycleBitmap(null,a);
  }
  ImageLoader.getInstance().cancelLoadingForImageReceiver(this,true);
}
