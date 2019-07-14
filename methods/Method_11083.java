/** 
 * Draws items
 * @param canvas the canvas for drawing
 */
private void drawItems(Canvas canvas){
  canvas.save();
  int top=(currentItem - firstItem) * getItemHeight() + (getItemHeight() - getHeight()) / 2;
  canvas.translate(PADDING,-top + scrollingOffset);
  itemsLayout.draw(canvas);
  canvas.restore();
}
