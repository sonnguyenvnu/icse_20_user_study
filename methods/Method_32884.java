public void resize(int columns,int rows){
  if (mRows == rows && mColumns == columns) {
    return;
  }
 else   if (columns < 2 || rows < 2) {
    throw new IllegalArgumentException("rows=" + rows + ", columns=" + columns);
  }
  if (mRows != rows) {
    mRows=rows;
    mTopMargin=0;
    mBottomMargin=mRows;
  }
  if (mColumns != columns) {
    int oldColumns=mColumns;
    mColumns=columns;
    boolean[] oldTabStop=mTabStop;
    mTabStop=new boolean[mColumns];
    setDefaultTabStops();
    int toTransfer=Math.min(oldColumns,columns);
    System.arraycopy(oldTabStop,0,mTabStop,0,toTransfer);
    mLeftMargin=0;
    mRightMargin=mColumns;
  }
  resizeScreen();
}
