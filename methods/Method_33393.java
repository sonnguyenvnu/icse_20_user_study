private void showError(ValidatorBase validator){
  errorLabel.setText(validator.getMessage());
  Node icon=validator.getIcon();
  errorIcon.getChildren().clear();
  if (icon != null) {
    errorIcon.getChildren().setAll(icon);
    StackPane.setAlignment(icon,Pos.CENTER_RIGHT);
  }
  setVisible(true);
}
