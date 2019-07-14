@Override public void show(){
  super.show();
  final ColorPicker colorPicker=(ColorPicker)getSkinnable();
  popupContent.updateSelection(colorPicker.getValue());
}
