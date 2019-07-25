@Override public void relayout(Component c){
  Font font=c.getFont();
  FontMetrics metrics=c.getFontMetrics(font);
  this.myStringWidth=metrics.stringWidth(this.myCaption);
  this.myCharHeight=metrics.getHeight();
  this.myWidth=this.myStringWidth + 2 * AbstractBlock.this.getPaddingX(metrics);
  this.myHeight=this.myCharHeight + 2 * AbstractBlock.this.getPaddingY(metrics);
}
