private boolean isNotA(NameOccurrence qualifier,Class<? extends AbstractJavaNode> type){
  ScopedNode location=qualifier.getLocation();
  return location == null || !(type.isAssignableFrom(location.getClass()));
}
