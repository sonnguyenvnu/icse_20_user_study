public static ColorWheelRenderer getRenderer(ColorPickerView.WHEEL_TYPE wheelType){
switch (wheelType) {
case CIRCLE:
    return new SimpleColorWheelRenderer();
case FLOWER:
  return new FlowerColorWheelRenderer();
default :
break;
}
throw new IllegalArgumentException("wrong WHEEL_TYPE");
}
