/** 
 * Updates painted label layout and returns clipped or full label text.
 * @param label  label to process
 * @param fm     label font metrics
 * @param width  label width
 * @param height label height
 * @return clipped or full label text
 */
protected String layout(final E label,final FontMetrics fm,final int width,final int height){
  final Insets insets=label.getInsets(null);
  final String text=label.getText();
  final Icon icon=(label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();
  final Rectangle paintViewR=new Rectangle();
  paintViewR.x=insets.left;
  paintViewR.y=insets.top;
  paintViewR.width=width - (insets.left + insets.right);
  paintViewR.height=height - (insets.top + insets.bottom);
  paintIconR.x=paintIconR.y=paintIconR.width=paintIconR.height=0;
  paintTextR.x=paintTextR.y=paintTextR.width=paintTextR.height=0;
  return layoutCL(label,fm,text,icon,paintViewR,paintIconR,paintTextR);
}
