private static void validateMeasureSpecs(int widthSpec,int heightSpec,boolean canRemeasure,int scrollDirection){
switch (scrollDirection) {
case HORIZONTAL:
    if (SizeSpec.getMode(widthSpec) == SizeSpec.UNSPECIFIED) {
      throw new IllegalStateException("Width mode has to be EXACTLY OR AT MOST for an horizontal scrolling RecyclerView");
    }
  if (!canRemeasure && SizeSpec.getMode(heightSpec) == SizeSpec.UNSPECIFIED) {
    throw new IllegalStateException("Can't use Unspecified height on an horizontal " + "scrolling Recycler if dynamic measurement is not allowed");
  }
break;
case VERTICAL:
if (SizeSpec.getMode(heightSpec) == SizeSpec.UNSPECIFIED) {
throw new IllegalStateException("Height mode has to be EXACTLY OR AT MOST for a vertical scrolling RecyclerView");
}
if (!canRemeasure && SizeSpec.getMode(widthSpec) == SizeSpec.UNSPECIFIED) {
throw new IllegalStateException("Can't use Unspecified width on a vertical scrolling " + "Recycler if dynamic measurement is not allowed");
}
break;
default :
throw new UnsupportedOperationException("The orientation defined by LayoutInfo should be" + " either OrientationHelper.HORIZONTAL or OrientationHelper.VERTICAL");
}
}
