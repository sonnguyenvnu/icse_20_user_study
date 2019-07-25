public void attach(@NotNull SModel model){
  model.getSource().addListener(this);
  updateTimestamp(model.getSource());
}
