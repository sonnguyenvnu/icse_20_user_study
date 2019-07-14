/** 
 * Re-initialize all of our variables that are dependent on the TextPaint measurements.
 */
private void onTextPaintMeasurementChanged(){
  metrics.invalidate();
  checkForRelayout();
  invalidate();
}
