private static void setCellGroup(LayoutParams lp,int row,int rowSpan,int col,int colSpan){
  lp.setRowSpecSpan(new Interval(row,row + rowSpan));
  lp.setColumnSpecSpan(new Interval(col,col + colSpan));
}
