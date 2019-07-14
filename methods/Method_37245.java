protected boolean hasSimpleProperty(final BeanProperty bp){
  if (bp.bean == null) {
    return false;
  }
  final Getter getter=bp.getGetter(isDeclared);
  if (getter != null) {
    return true;
  }
  if (bp.isMap()) {
    Map map=(Map)bp.bean;
    if (map.containsKey(bp.name)) {
      return true;
    }
  }
  return false;
}
