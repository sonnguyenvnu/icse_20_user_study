public void detach(@NotNull SModel model){
  model.getSource().removeListener(this);
}
