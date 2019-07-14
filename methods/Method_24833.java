public SketchInterval mapJavaToSketch(int startJavaOffset,int stopJavaOffset){
  int length=stopJavaOffset - startJavaOffset;
  int startPdeOffset=javaOffsetToPdeOffset(startJavaOffset);
  int stopPdeOffset;
  if (length == 0) {
    stopPdeOffset=startPdeOffset;
  }
 else {
    stopPdeOffset=javaOffsetToPdeOffset(stopJavaOffset - 1);
    if (stopPdeOffset >= 0 && (stopPdeOffset > startPdeOffset || length == 1)) {
      stopPdeOffset+=1;
    }
  }
  if (startPdeOffset < 0 || stopPdeOffset < 0) {
    return SketchInterval.BEFORE_START;
  }
  int tabIndex=pdeOffsetToTabIndex(startPdeOffset);
  if (startPdeOffset >= pdeCode.length()) {
    startPdeOffset=pdeCode.length() - 1;
    stopPdeOffset=startPdeOffset + 1;
  }
  return new SketchInterval(tabIndex,pdeOffsetToTabOffset(tabIndex,startPdeOffset),pdeOffsetToTabOffset(tabIndex,stopPdeOffset),startPdeOffset,stopPdeOffset);
}
