protected void updateControlPosition(){
  if (this.position.get() == RipplerPos.BACK) {
    ripplerPane.toBack();
  }
 else {
    ripplerPane.toFront();
  }
}
