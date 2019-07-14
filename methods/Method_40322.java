/** 
 * Returns the previous component of this qname chain, or  {@code null} ifthis is the first component.
 */
public Qname getPrevious(){
  Node parent=getParent();
  if (parent instanceof Qname) {
    return (Qname)parent;
  }
  return null;
}
