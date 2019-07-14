public static int resolveSizeAndState(int size,int measureSpec,int childMeasuredState){
  int result=size;
  int specMode=MeasureSpec.getMode(measureSpec);
  int specSize=MeasureSpec.getSize(measureSpec);
switch (specMode) {
case MeasureSpec.UNSPECIFIED:
    result=size;
  break;
case MeasureSpec.AT_MOST:
if (specSize < size) {
  result=specSize | 16777216;
}
 else {
  result=size;
}
break;
case MeasureSpec.EXACTLY:
result=specSize;
break;
}
return result | (childMeasuredState & (-16777216));
}
