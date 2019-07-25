protected void repaint(int y,int height){
  int foldingLineX=getLeftHighlighter().getFoldingLineX();
  myLeftHighlighter.repaint(foldingLineX - getLeftAreaWidth(),y,foldingLineX + getRightAreaWidth() + 1,height + 1);
}
