private int calculateRowHeight(List<Spanned> row){
  if (row.size() == 0) {
    return 0;
  }
  TextPaint textPaint=getTextPaint();
  int columnWidth=tableWidth / row.size();
  int rowHeight=0;
  for (  Spanned cell : row) {
    StaticLayout layout=new StaticLayout(cell,textPaint,columnWidth - 2 * PADDING,Alignment.ALIGN_NORMAL,1.5f,0.5f,true);
    if (layout.getHeight() > rowHeight) {
      rowHeight=layout.getHeight();
    }
  }
  return rowHeight;
}
