public void setUseController(boolean useController){
  if (this.useController == useController) {
    return;
  }
  this.useController=useController;
  if (useController) {
    controller.setPlayer(player);
  }
 else {
    controller.hide();
    controller.setPlayer(null);
  }
}
