/** 
 * This implementation delegates to  {@link #equals(Object)}, per the Saxon documentation's description of this method's behavior. {@inheritDoc}
 */
@Override public boolean isSameNodeInfo(NodeInfo other){
  return this.equals(other);
}
