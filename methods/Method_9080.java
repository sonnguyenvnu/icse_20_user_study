public void addChild(TLRPC.TL_pageTableCell cell,int x,int y,int colspan){
  if (colspan == 0) {
    colspan=1;
  }
  Child child=new Child(childrens.size());
  child.cell=cell;
  LayoutParams layoutParams=new LayoutParams();
  layoutParams.rowSpec=new Spec(false,new Interval(y,y + (cell.rowspan != 0 ? cell.rowspan : 1)),FILL,0.0f);
  layoutParams.columnSpec=new Spec(false,new Interval(x,x + colspan),FILL,1.0f);
  child.layoutParams=layoutParams;
  childrens.add(child);
  if (cell.rowspan > 1) {
    rowSpans.add(new Point(y,y + cell.rowspan));
  }
  invalidateStructure();
}
