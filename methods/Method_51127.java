public boolean isBranch(){
  return this.getLast().getChildren().size() > 1;
}
