public void setIndeterminateDrawable(Sprite d){
  super.setIndeterminateDrawable(d);
  mSprite=d;
  if (mSprite.getColor() == 0) {
    mSprite.setColor(mColor);
  }
  onSizeChanged(getWidth(),getHeight(),getWidth(),getHeight());
  if (getVisibility() == VISIBLE) {
    mSprite.start();
  }
}
