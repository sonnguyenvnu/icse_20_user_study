@OnMeasure static void onMeasure(ComponentContext c,ComponentLayout layout,int widthSpec,int heightSpec,Size size,@Prop(resType=ResType.DRAWABLE) Drawable drawable){
  if (drawable == null || drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
    size.width=0;
    size.height=0;
    return;
  }
  final int intrinsicHeight=drawable.getIntrinsicHeight();
  final int intrinsicWidth=drawable.getIntrinsicWidth();
  if (SizeSpec.getMode(widthSpec) == UNSPECIFIED && SizeSpec.getMode(heightSpec) == UNSPECIFIED) {
    size.width=intrinsicWidth;
    size.height=intrinsicHeight;
    return;
  }
  final float aspectRatio=intrinsicWidth / (float)intrinsicHeight;
  MeasureUtils.measureWithAspectRatio(widthSpec,heightSpec,intrinsicWidth,intrinsicHeight,aspectRatio,size);
}
