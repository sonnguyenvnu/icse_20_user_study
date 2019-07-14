/** 
 * Using the trigonometric Unit Circle, calculate the positions that the text will need to be drawn at based on the specified circle radius. Place the values in the textGridHeights and textGridWidths parameters.
 */
private void calculateGridSizes(float numbersRadius,float xCenter,float yCenter,float textSize,float[] textGridHeights,float[] textGridWidths){
  float offset1=numbersRadius;
  float offset2=numbersRadius * ((float)Math.sqrt(3)) / 2f;
  float offset3=numbersRadius / 2f;
  mPaint.setTextSize(textSize);
  yCenter-=(mPaint.descent() + mPaint.ascent()) / 2;
  textGridHeights[0]=yCenter - offset1;
  textGridWidths[0]=xCenter - offset1;
  textGridHeights[1]=yCenter - offset2;
  textGridWidths[1]=xCenter - offset2;
  textGridHeights[2]=yCenter - offset3;
  textGridWidths[2]=xCenter - offset3;
  textGridHeights[3]=yCenter;
  textGridWidths[3]=xCenter;
  textGridHeights[4]=yCenter + offset3;
  textGridWidths[4]=xCenter + offset3;
  textGridHeights[5]=yCenter + offset2;
  textGridWidths[5]=xCenter + offset2;
  textGridHeights[6]=yCenter + offset1;
  textGridWidths[6]=xCenter + offset1;
}
