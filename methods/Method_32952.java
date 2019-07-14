@Override public Node build(){
  leftButton=new JFXButton("Alert");
  leftButton.setLayoutX(50);
  leftButton.setLayoutY(50);
  return new Group(leftButton);
}
