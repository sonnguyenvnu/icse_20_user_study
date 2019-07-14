private int getTextOffsetAt(int x,int y){
  final int line=mLayout.getLineForVertical(y);
  final float left;
  final float right;
  if (mLayout.getAlignment() == Layout.Alignment.ALIGN_CENTER) {
    left=mLayout.getLineLeft(line);
    right=mLayout.getLineRight(line);
  }
 else {
    final boolean rtl=mLayout.getParagraphDirection(line) == Layout.DIR_RIGHT_TO_LEFT;
    left=rtl ? mLayout.getWidth() - mLayout.getLineMax(line) : mLayout.getParagraphLeft(line);
    right=rtl ? mLayout.getParagraphRight(line) : mLayout.getLineMax(line);
  }
  if (x < left || x > right) {
    return -1;
  }
  try {
    return mLayout.getOffsetForHorizontal(line,x);
  }
 catch (  ArrayIndexOutOfBoundsException e) {
    return -1;
  }
}
