@Override public AxisIterator iterateAxis(byte axisNumber){
  throw createUnsupportedOperationException("NodeInfo.iterateAxis(byte) for axis '" + Axis.axisName[axisNumber] + "'");
}
