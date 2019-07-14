/** 
 * Set the  {@param outputSize} to respect both Specs and try to keep both width and height equal.This will only not guarantee equal width and height if thes Specs use modes and sizes which prevent it.
 */
public static void measureWithEqualDimens(int widthSpec,int heightSpec,Size outputSize){
  final int widthMode=SizeSpec.getMode(widthSpec);
  final int widthSize=SizeSpec.getSize(widthSpec);
  final int heightMode=SizeSpec.getMode(heightSpec);
  final int heightSize=SizeSpec.getSize(heightSpec);
  if (widthMode == UNSPECIFIED && heightMode == UNSPECIFIED) {
    outputSize.width=0;
    outputSize.height=0;
    if (ComponentsConfiguration.IS_INTERNAL_BUILD) {
      Log.d(TAG,"Default to size {0, 0} because both width and height are UNSPECIFIED");
    }
    return;
  }
  if (widthMode == EXACTLY) {
    outputSize.width=widthSize;
switch (heightMode) {
case EXACTLY:
      outputSize.height=heightSize;
    return;
case AT_MOST:
  outputSize.height=Math.min(widthSize,heightSize);
return;
case UNSPECIFIED:
outputSize.height=widthSize;
return;
}
}
 else if (widthMode == AT_MOST) {
switch (heightMode) {
case EXACTLY:
outputSize.height=heightSize;
outputSize.width=Math.min(widthSize,heightSize);
return;
case AT_MOST:
final int chosenSize=Math.min(widthSize,heightSize);
outputSize.width=chosenSize;
outputSize.height=chosenSize;
return;
case UNSPECIFIED:
outputSize.width=widthSize;
outputSize.height=widthSize;
return;
}
}
outputSize.height=heightSize;
outputSize.width=heightSize;
}
