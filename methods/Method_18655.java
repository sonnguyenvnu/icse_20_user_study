/** 
 * Sets the flags corresponding to the edges of the component that are visible. Afterwards, it checks if the component has entered the full impression visible range and, if so, it sets the appropriate flag.
 */
void setVisibleEdges(Rect componentBounds,Rect componentVisibleBounds){
  if (componentBounds.top == componentVisibleBounds.top) {
    mFlags|=FLAG_TOP_EDGE_VISIBLE;
  }
  if (componentBounds.bottom == componentVisibleBounds.bottom) {
    mFlags|=FLAG_BOTTOM_EDGE_VISIBLE;
  }
  if (componentBounds.left == componentVisibleBounds.left) {
    mFlags|=FLAG_LEFT_EDGE_VISIBLE;
  }
  if (componentBounds.right == componentVisibleBounds.right) {
    mFlags|=FLAG_RIGHT_EDGE_VISIBLE;
  }
}
