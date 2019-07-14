/** 
 * Resolves nested property name to the very last indexed property. If forced, <code>null</code> or non-existing properties will be created.
 */
protected void resolveNestedProperties(final BeanProperty bp){
  String name=bp.name;
  int dotNdx;
  while ((dotNdx=indexOfDot(name)) != -1) {
    bp.last=false;
    bp.setName(name.substring(0,dotNdx));
    bp.updateBean(getIndexProperty(bp));
    name=name.substring(dotNdx + 1);
  }
  bp.last=true;
  bp.setName(name);
}
