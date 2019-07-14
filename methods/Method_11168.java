private void initCallBack(){
  if (sprites != null) {
    for (    Sprite sprite : sprites) {
      sprite.setCallback(this);
    }
  }
}
