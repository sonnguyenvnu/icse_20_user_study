/** 
 * Returns whether the specified point is within bounds of this popup layer or not.
 * @param x X coordinate
 * @param y Y coordinate
 * @return true if the specified point is within bounds of this popup layer, false otherwise
 */
@Override public boolean contains(final int x,final int y){
  return normalContains(x,y);
}
