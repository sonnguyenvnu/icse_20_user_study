/** 
 * Iterates over a jQuery object, executing a function for each matched element.
 * @see #each(JerryFunction)
 */
public Jerry eachNode(final JerryNodeFunction function){
  for (int i=0; i < nodes.length; i++) {
    Node node=nodes[i];
    if (!function.onNode(node,i)) {
      break;
    }
  }
  return this;
}
