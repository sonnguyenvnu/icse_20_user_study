public void updateDisabled(){
  final boolean disabled=control.isDisable();
  line.setBorder(!disabled ? Border.EMPTY : new Border(new BorderStroke(control.getUnFocusColor(),BorderStrokeStyle.DASHED,CornerRadii.EMPTY,new BorderWidths(1))));
  line.setBackground(new Background(new BackgroundFill(disabled ? Color.TRANSPARENT : control.getUnFocusColor(),CornerRadii.EMPTY,Insets.EMPTY)));
}
