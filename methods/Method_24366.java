@Override public int[] getVertexCodes(){
  if (family == GROUP)   return null;
 else {
    if (family == PRIMITIVE || family == PATH) {
      updateTessellation();
    }
    if (inGeo.codes == null)     return null;
    return inGeo.codes;
  }
}
