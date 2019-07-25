public PolylineBreakeable copy(Pointable newStart,Pointable newEnd){
  final PolylineBreakeable result=new PolylineBreakeable(newStart,newEnd);
  result.breakures.addAll(this.breakures);
  return result;
}
