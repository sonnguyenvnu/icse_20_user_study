/** 
 * Dynamic height of completion panel depending on item count
 */
private int calcHeight(int itemCount){
  int maxHeight=250;
  FontMetrics fm=textarea.getGraphics().getFontMetrics();
  float itemHeight=Math.max((fm.getHeight() + (fm.getDescent()) * 0.5f),classIcon.getIconHeight() * 1.2f);
  if (horizontalScrollBarVisible) {
    itemCount++;
  }
  if (itemCount < 4) {
    itemHeight*=1.3f;
  }
  float h=itemHeight * (itemCount);
  if (itemCount >= 4) {
    h+=itemHeight * 0.3;
  }
  return Math.min(maxHeight,(int)h);
}
