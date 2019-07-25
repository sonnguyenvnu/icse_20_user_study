public UEllipse bigger(double more){
  final UEllipse result=new UEllipse(width + more,height + more);
  result.setDeltaShadow(getDeltaShadow());
  return result;
}
