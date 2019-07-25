private void grow(Set<ClosedArea> areas){
  for (  ClosedArea area : new HashSet<ClosedArea>(areas)) {
    if (area.isClosed() == false) {
      areas.addAll(buildClosedArea(area));
    }
  }
}
