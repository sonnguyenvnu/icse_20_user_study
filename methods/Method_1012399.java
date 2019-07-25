public void recycle(){
  targets.clear();
  for (  GifDrawable gifDrawable : gifDrawables) {
    gifDrawable.setCallback(null);
    gifDrawable.recycle();
  }
  gifDrawables.clear();
}
