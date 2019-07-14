private int calcWidth(){
  int maxWidth=300;
  float min=0;
  FontMetrics fm=textarea.getGraphics().getFontMetrics();
  for (int i=0; i < completionList.getModel().getSize(); i++) {
    float h=fm.stringWidth(completionList.getModel().getElementAt(i).getLabel());
    min=Math.max(min,h);
  }
  int w=Math.min((int)min,maxWidth);
  horizontalScrollBarVisible=(w == maxWidth);
  w+=classIcon.getIconWidth();
  w+=fm.stringWidth("           ");
  return w;
}
