public static LabelBuilt icon(Ikon ikon,double minSize){
  Label iconLabel=new Label();
  iconLabel.setGraphic(new FontIcon(ikon));
  iconLabel.setMinWidth(minSize);
  return new LabelBuilt(iconLabel);
}
