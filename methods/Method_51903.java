/** 
 * Returns true if this type parameter is bounded, in which case  {@link #getTypeBoundNode()} doesn'treturn  {@code null}.
 */
public final boolean hasTypeBound(){
  return getTypeBoundNode() != null;
}
