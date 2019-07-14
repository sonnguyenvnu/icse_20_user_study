/** 
 * One of VERTEX, BEZIER_VERTEX, CURVE_VERTEX, or BREAK.
 */
@Override public int getVertexCode(int index){
  return inGeo.codes[index];
}
