/** 
 * Draw the 12 text values at the positions specified by the textGrid parameters.
 */
private void drawTexts(Canvas canvas,float textSize,Typeface typeface,String[] texts,float[] textGridWidths,float[] textGridHeights){
  mPaint.setTextSize(textSize);
  mPaint.setTypeface(typeface);
  canvas.drawText(texts[0],textGridWidths[3],textGridHeights[0],mPaint);
  canvas.drawText(texts[1],textGridWidths[4],textGridHeights[1],mPaint);
  canvas.drawText(texts[2],textGridWidths[5],textGridHeights[2],mPaint);
  canvas.drawText(texts[3],textGridWidths[6],textGridHeights[3],mPaint);
  canvas.drawText(texts[4],textGridWidths[5],textGridHeights[4],mPaint);
  canvas.drawText(texts[5],textGridWidths[4],textGridHeights[5],mPaint);
  canvas.drawText(texts[6],textGridWidths[3],textGridHeights[6],mPaint);
  canvas.drawText(texts[7],textGridWidths[2],textGridHeights[5],mPaint);
  canvas.drawText(texts[8],textGridWidths[1],textGridHeights[4],mPaint);
  canvas.drawText(texts[9],textGridWidths[0],textGridHeights[3],mPaint);
  canvas.drawText(texts[10],textGridWidths[1],textGridHeights[2],mPaint);
  canvas.drawText(texts[11],textGridWidths[2],textGridHeights[1],mPaint);
}
