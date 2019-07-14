/** 
 * Returns true if the component associated with this VisibilityItem is in the full impression range.
 */
boolean isInFullImpressionRange(){
  final int allEdgesVisible=FLAG_LEFT_EDGE_VISIBLE | FLAG_TOP_EDGE_VISIBLE | FLAG_RIGHT_EDGE_VISIBLE | FLAG_BOTTOM_EDGE_VISIBLE;
  return (mFlags & allEdgesVisible) == allEdgesVisible;
}
