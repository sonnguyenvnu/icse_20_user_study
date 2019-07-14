public void registerSnackbarContainer(Pane snackbarContainer){
  if (snackbarContainer != null) {
    if (this.snackbarContainer != null) {
      throw new IllegalArgumentException("Snackbar Container already set");
    }
    this.snackbarContainer=snackbarContainer;
    this.snackbarContainer.getChildren().add(this);
    this.snackbarContainer.heightProperty().addListener(weakSizeListener);
    this.snackbarContainer.widthProperty().addListener(weakSizeListener);
  }
}
