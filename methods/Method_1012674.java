public void update(){
  if (isMode(AUTOSAVE)) {
    save();
  }
  getChildren().clear();
  setDiscovered(false);
  if (list.size() < 1 && !isMode(PERMANENT)) {
    getParent().getChildren().remove(this);
  }
}
