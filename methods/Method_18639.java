/** 
 * Set the  {@param outputSize} to respect both Specs and the desired width and height.The desired size is usually the necessary pixels to render the inner content.
 */
public static void measureWithDesiredPx(int widthSpec,int heightSpec,int desiredWidthPx,int desiredHeightPx,Size outputSize){
  outputSize.width=getResultSizePxWithSpecAndDesiredPx(widthSpec,desiredWidthPx);
  outputSize.height=getResultSizePxWithSpecAndDesiredPx(heightSpec,desiredHeightPx);
}
