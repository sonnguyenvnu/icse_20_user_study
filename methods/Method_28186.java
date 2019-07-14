@Override public boolean isCollapsed(long position){
  Boolean toggle=toggleMap.get(position);
  return toggle != null && toggle;
}
