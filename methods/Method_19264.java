@OnMeasure static void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size){
  if (SizeSpec.getMode(widthSpec) == SizeSpec.UNSPECIFIED && SizeSpec.getMode(heightSpec) == SizeSpec.UNSPECIFIED) {
    size.width=DEFAULT_SIZE;
    size.height=DEFAULT_SIZE;
  }
 else {
    MeasureUtils.measureWithEqualDimens(widthSpec,heightSpec,size);
  }
}
