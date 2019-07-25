@Override public List<RealClassPathItem> flatten(){
  List<RealClassPathItem> result=new ArrayList<>();
  result.add(this);
  return result;
}
