/** 
 * @param pos A zero-based button index with 0 as the rightmost button
 */
private void drawButton(Graphics g,String symbol,int pos,boolean highlight){
  int left=sizeW - (pos + 1) * buttonSize;
  g.drawImage(bgImage[mode],left,0,buttonSize,sizeH,this);
  if (highlight) {
    g.setColor(urlColor);
  }
 else {
    g.setColor(fgColor[mode]);
  }
  g.drawString(symbol,left + (buttonSize - g.getFontMetrics().stringWidth(symbol)) / 2,(sizeH + ascent) / 2);
}
