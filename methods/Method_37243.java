protected boolean resolveExistingNestedProperties(final BeanProperty bp){
  String name=bp.name;
  int dotNdx;
  while ((dotNdx=indexOfDot(name)) != -1) {
    bp.last=false;
    bp.setName(name.substring(0,dotNdx));
    final String temp=bp.name;
    if (!hasIndexProperty(bp)) {
      return false;
    }
    bp.setName(temp);
    bp.updateBean(getIndexProperty(bp));
    name=name.substring(dotNdx + 1);
  }
  bp.last=true;
  bp.setName(name);
  return true;
}
