private void init(StringBounder stringBounder){
  if (rowsStart != null) {
    return;
  }
  final double titleHeight=title.calculateDimension(stringBounder).getHeight();
  rowsStart=new double[rows + 1];
  rowsStart[0]=titleHeight / 2;
  for (int i=1; i < rows + 1; i++) {
    rowsStart[i]=titleHeight / 2;
  }
  colsStart=new double[cols + 1];
  final List<Cell> all=new ArrayList<Cell>(positions1.values());
  Collections.sort(all,new LeftFirst());
  for (  Cell cell : all) {
    final Element elt=positions2.get(cell);
    final Dimension2D dim=elt.getPreferredDimension(stringBounder,0,0);
    ensureColWidth(cell.getMinCol(),cell.getMaxCol() + 1,dim.getWidth() + 2);
  }
  Collections.sort(all,new TopFirst());
  for (  Cell cell : all) {
    final Element elt=positions2.get(cell);
    final double supY=cell.getMinRow() == 0 ? titleHeight / 2 : 0;
    final Dimension2D dim=elt.getPreferredDimension(stringBounder,0,0);
    ensureRowHeight(cell.getMinRow(),cell.getMaxRow() + 1,dim.getHeight() + supY + 2);
  }
}
