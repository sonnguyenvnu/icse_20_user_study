public void unregisterSnackbarContainer(Pane snackbarContainer){
  if (snackbarContainer != null) {
    if (this.snackbarContainer == null) {
      throw new IllegalArgumentException("Snackbar Container not set");
    }
    this.snackbarContainer.getChildren().remove(this);
    this.snackbarContainer.heightProperty().removeListener(weakSizeListener);
    this.snackbarContainer.widthProperty().removeListener(weakSizeListener);
    this.snackbarContainer=null;
  }
}
