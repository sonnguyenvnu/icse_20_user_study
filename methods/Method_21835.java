/** 
 * Clamp the size to the measure spec.
 * @param size Size we want to be
 * @param spec Measure spec to clamp against
 * @return the appropriate size to pass to {@linkplain View#setMeasuredDimension(int,int)}
 */
private static int clampSize(int size,int spec){
  int specMode=MeasureSpec.getMode(spec);
  int specSize=MeasureSpec.getSize(spec);
switch (specMode) {
case MeasureSpec.EXACTLY:
{
      return specSize;
    }
case MeasureSpec.AT_MOST:
{
    return Math.min(size,specSize);
  }
case MeasureSpec.UNSPECIFIED:
default :
{
  return size;
}
}
}
