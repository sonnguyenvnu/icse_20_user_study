@Override public MappeableArrayContainer iand(final MappeableArrayContainer value2){
  final MappeableArrayContainer value1=this;
  if (!BufferUtil.isBackedBySimpleArray(value1.content)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  value1.cardinality=BufferUtil.unsignedIntersect2by2(value1.content,value1.getCardinality(),value2.content,value2.getCardinality(),value1.content.array());
  return this;
}
