public void balance(){
  if (elements.size() == 0) {
    return;
  }
  if (minY != Double.MAX_VALUE) {
    throw new IllegalStateException();
  }
  for (  SymetricalTeePositioned element : elements) {
    minY=Math.min(minY,element.getMinY());
    maxY=Math.max(maxY,element.getMaxY());
  }
  final double mean=(minY + maxY) / 2;
  for (  SymetricalTeePositioned stp : elements) {
    stp.move(-mean);
  }
}
