/** 
 * Returns the edge label of this edge
 * @return edge label of this edge
 */
default EdgeLabel edgeLabel(){
  assert getType() instanceof EdgeLabel;
  return (EdgeLabel)getType();
}
