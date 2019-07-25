private List<IGroup> reverse(Collection<IGroup> source){
  final List<IGroup> result=new ArrayList<IGroup>();
  for (  IGroup g : source) {
    result.add(0,g);
  }
  return result;
}
