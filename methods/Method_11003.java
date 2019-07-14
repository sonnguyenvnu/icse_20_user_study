public ColorPickerDialogBuilder wheelType(ColorPickerView.WHEEL_TYPE wheelType){
  ColorWheelRenderer renderer=ColorWheelRendererBuilder.getRenderer(wheelType);
  colorPickerView.setRenderer(renderer);
  return this;
}
