protected void positionControl(Node control){
  if (this.position.get() == RipplerPos.BACK) {
    getChildren().add(control);
  }
 else {
    getChildren().add(0,control);
  }
}
