/** 
 * Measure according to an aspect ratio an width and height constraints.
 * @param widthSpec A SizeSpec for the width
 * @param heightSpec A SizeSpec for the height
 * @param aspectRatio The aspect ration size against
 * @param outputSize The output size of this measurement
 */
public static void measureWithAspectRatio(int widthSpec,int heightSpec,float aspectRatio,Size outputSize){
  final int widthMode=SizeSpec.getMode(widthSpec);
  final int widthSize=SizeSpec.getSize(widthSpec);
  final int heightMode=SizeSpec.getMode(heightSpec);
  final int heightSize=SizeSpec.getSize(heightSpec);
  final int widthBasedHeight=(int)Math.ceil(widthSize / aspectRatio);
  final int heightBasedWidth=(int)Math.ceil(heightSize * aspectRatio);
  if (widthMode == UNSPECIFIED && heightMode == UNSPECIFIED) {
    outputSize.width=0;
    outputSize.height=0;
    if (ComponentsConfiguration.IS_INTERNAL_BUILD) {
      Log.d(TAG,"Default to size {0, 0} because both width and height are UNSPECIFIED");
    }
    return;
  }
  if (widthMode == AT_MOST && heightMode == AT_MOST) {
    if (widthBasedHeight > heightSize) {
      outputSize.width=heightBasedWidth;
      outputSize.height=heightSize;
    }
 else {
      outputSize.width=widthSize;
      outputSize.height=widthBasedHeight;
    }
  }
 else   if (widthMode == EXACTLY) {
    outputSize.width=widthSize;
    if (heightMode == UNSPECIFIED || widthBasedHeight <= heightSize) {
      outputSize.height=widthBasedHeight;
    }
 else {
      outputSize.height=heightSize;
      if (ComponentsConfiguration.IS_INTERNAL_BUILD) {
        Log.d(TAG,String.format("Ratio makes height larger than allowed. w:%s h:%s aspectRatio:%f",SizeSpec.toString(widthSpec),SizeSpec.toString(heightSpec),aspectRatio));
      }
    }
  }
 else   if (heightMode == EXACTLY) {
    outputSize.height=heightSize;
    if (widthMode == UNSPECIFIED || heightBasedWidth <= widthSize) {
      outputSize.width=heightBasedWidth;
    }
 else {
      outputSize.width=widthSize;
      if (ComponentsConfiguration.IS_INTERNAL_BUILD) {
        Log.d(TAG,String.format("Ratio makes width larger than allowed. w:%s h:%s aspectRatio:%f",SizeSpec.toString(widthSpec),SizeSpec.toString(heightSpec),aspectRatio));
      }
    }
  }
 else   if (widthMode == AT_MOST) {
    outputSize.width=widthSize;
    outputSize.height=widthBasedHeight;
  }
 else   if (heightMode == AT_MOST) {
    outputSize.width=heightBasedWidth;
    outputSize.height=heightSize;
  }
}
