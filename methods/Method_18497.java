public static int makeSizeSpecFromCssSpec(float cssSize,YogaMeasureMode cssMode){
switch (cssMode) {
case EXACTLY:
    return makeSizeSpec(FastMath.round(cssSize),SizeSpec.EXACTLY);
case UNDEFINED:
  return makeSizeSpec(0,SizeSpec.UNSPECIFIED);
case AT_MOST:
return makeSizeSpec(FastMath.round(cssSize),SizeSpec.AT_MOST);
default :
throw new IllegalArgumentException("Unexpected YogaMeasureMode: " + cssMode);
}
}
