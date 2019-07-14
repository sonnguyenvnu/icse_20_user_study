protected void updatePosition(){
  float halfWidth=getWidth() / 2.0f;
  float halfHeight=getHeight() / 2.0f;
  setX(position.x - halfWidth);
  setY(position.y - halfHeight);
  updateSelectionView();
}
