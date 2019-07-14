private int makeMeasureSpec(int measureSpec,int maxSize){
  if (maxSize == SIZE_UNSPECIFIED) {
    return measureSpec;
  }
  final int size=MeasureSpec.getSize(measureSpec);
  final int mode=MeasureSpec.getMode(measureSpec);
switch (mode) {
case MeasureSpec.EXACTLY:
    return measureSpec;
case MeasureSpec.AT_MOST:
  return MeasureSpec.makeMeasureSpec(Math.min(size,maxSize),MeasureSpec.EXACTLY);
case MeasureSpec.UNSPECIFIED:
return MeasureSpec.makeMeasureSpec(maxSize,MeasureSpec.EXACTLY);
default :
throw new IllegalArgumentException("Unknown measure mode: " + mode);
}
}
