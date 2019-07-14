/** 
 * This implementation calls  {@link #iterateAxis(byte)} to get an{@link AxisIterator} which is then optionally filtered using{@link AxisFilter}. {@inheritDoc}
 */
@Override public AxisIterator iterateAxis(byte axisNumber,NodeTest nodeTest){
  AxisIterator axisIterator=iterateAxis(axisNumber);
  if (nodeTest != null) {
    axisIterator=new AxisFilter(axisIterator,nodeTest);
  }
  return axisIterator;
}
