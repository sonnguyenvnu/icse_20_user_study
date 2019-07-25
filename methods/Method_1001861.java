static PointListIterator create(SvgResult svg,int lineColor){
  final PointListIteratorImpl result=new PointListIteratorImpl(svg);
  final int idx=svg.getIndexFromColor(lineColor);
  if (idx == -1) {
    result.pos=-1;
  }
  return result;
}
