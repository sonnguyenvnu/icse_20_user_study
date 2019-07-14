public static int getViewMeasureSpec(int sizeSpec){
switch (getMode(sizeSpec)) {
case SizeSpec.EXACTLY:
    return MeasureSpec.makeMeasureSpec(getSize(sizeSpec),MeasureSpec.EXACTLY);
case SizeSpec.AT_MOST:
  return MeasureSpec.makeMeasureSpec(getSize(sizeSpec),MeasureSpec.AT_MOST);
case SizeSpec.UNSPECIFIED:
return MeasureSpec.makeMeasureSpec(getSize(sizeSpec),MeasureSpec.UNSPECIFIED);
default :
throw new IllegalStateException("Unexpected size spec mode");
}
}
