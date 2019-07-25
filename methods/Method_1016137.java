public void rotate(GeneralPath terrain){
  changeRotation(1);
  fillPlacement();
  if (intersects(terrain)) {
    changeRotation(-1);
    fillPlacement();
  }
}
