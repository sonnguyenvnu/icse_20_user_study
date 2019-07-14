void setControllerRouter(@NonNull Controller controller){
  controller.setRouter(this);
  controller.onContextAvailable();
}
