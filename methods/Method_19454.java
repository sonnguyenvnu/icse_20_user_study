@VisibleForTesting public static int resolveWidth(int widthSpec,Layout layout,boolean minimallyWide,int minimallyWideThreshold){
  final int fullWidth=SizeSpec.resolveSize(widthSpec,layout.getWidth());
  if (minimallyWide && layout.getLineCount() > 1) {
    final int minimalWidth=SizeSpec.resolveSize(widthSpec,LayoutMeasureUtil.getWidth(layout));
    if (fullWidth - minimalWidth > minimallyWideThreshold) {
      return minimalWidth;
    }
  }
  return fullWidth;
}
