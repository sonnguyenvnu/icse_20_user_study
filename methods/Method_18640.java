private static int getResultSizePxWithSpecAndDesiredPx(int spec,int desiredSize){
  final int mode=SizeSpec.getMode(spec);
switch (mode) {
case SizeSpec.UNSPECIFIED:
    return desiredSize;
case SizeSpec.AT_MOST:
  return Math.min(SizeSpec.getSize(spec),desiredSize);
case SizeSpec.EXACTLY:
return SizeSpec.getSize(spec);
default :
throw new IllegalStateException("Unexpected size spec mode");
}
}
