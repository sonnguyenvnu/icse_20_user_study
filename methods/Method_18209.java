private byte getIndex(int edgeEnumValue){
  return (byte)((mEdgesToValuesIndex >> (edgeEnumValue * 4)) & INDEX_MASK);
}
