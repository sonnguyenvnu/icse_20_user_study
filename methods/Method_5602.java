private void getEventTimes(TreeSet<Long> out,boolean descendsPNode){
  boolean isPNode=TAG_P.equals(tag);
  boolean isDivNode=TAG_DIV.equals(tag);
  if (descendsPNode || isPNode || (isDivNode && imageId != null)) {
    if (startTimeUs != C.TIME_UNSET) {
      out.add(startTimeUs);
    }
    if (endTimeUs != C.TIME_UNSET) {
      out.add(endTimeUs);
    }
  }
  if (children == null) {
    return;
  }
  for (int i=0; i < children.size(); i++) {
    children.get(i).getEventTimes(out,descendsPNode || isPNode);
  }
}
