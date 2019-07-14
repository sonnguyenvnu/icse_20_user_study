private void removeRadio(){
  for (int i=0; i < getChildren().size(); i++) {
    if (getChildren().get(i).getStyleClass().contains("radio")) {
      getChildren().remove(i);
      break;
    }
  }
}
