private void setControllersInternal(@Nullable DraweeController controller,@Nullable DraweeController hugeImageController){
  removeControllerListener(getController());
  addControllerListener(controller);
  mHugeImageController=hugeImageController;
  super.setController(controller);
}
