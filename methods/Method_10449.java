/** 
 * ??ScaleView???
 * @param heightMeasureSpec
 * @return
 */
private int getHeightSize(int heightMeasureSpec){
  int mode=MeasureSpec.getMode(heightMeasureSpec);
  int height=0;
switch (mode) {
case MeasureSpec.AT_MOST:
    height=textSize + scaleSpaceText + scaleHeight;
  break;
case MeasureSpec.EXACTLY:
{
  height=MeasureSpec.getSize(heightMeasureSpec);
  break;
}
case MeasureSpec.UNSPECIFIED:
{
height=Math.max(textSize + scaleSpaceText + scaleHeight,MeasureSpec.getSize(heightMeasureSpec));
break;
}
default :
break;
}
return height;
}
