/** 
 * Returns true if the metric can be computed on this operation. By default, abstract operations are filtered out.
 * @param node The operation
 * @return True if the metric can be computed on this operation
 */
public boolean supports(MethodLikeNode node){
  return !node.isAbstract();
}
