/** 
 * Measure according to an aspect ratio an width and height constraints. This version of measureWithAspectRatio will respect the intrinsic size of the component being measured.
 * @param widthSpec A SizeSpec for the width
 * @param heightSpec A SizeSpec for the height
 * @param intrinsicWidth A pixel value for the intrinsic width of the measured component
 * @param intrinsicHeight A pixel value for the intrinsic height of the measured component
 * @param aspectRatio The aspect ration size against
 * @param outputSize The output size of this measurement
 */
public static void measureWithAspectRatio(int widthSpec,int heightSpec,int intrinsicWidth,int intrinsicHeight,float aspectRatio,Size outputSize){
  if (SizeSpec.getMode(widthSpec) == AT_MOST && SizeSpec.getSize(widthSpec) > intrinsicWidth) {
    widthSpec=SizeSpec.makeSizeSpec(intrinsicWidth,AT_MOST);
  }
  if (SizeSpec.getMode(heightSpec) == AT_MOST && SizeSpec.getSize(heightSpec) > intrinsicHeight) {
    heightSpec=SizeSpec.makeSizeSpec(intrinsicHeight,AT_MOST);
  }
  measureWithAspectRatio(widthSpec,heightSpec,aspectRatio,outputSize);
}
