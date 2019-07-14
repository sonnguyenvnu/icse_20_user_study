/** 
 * {@inheritDoc}<p> <p>This default implementation adds compatibility with the previous way to get the xpath node name, which used  {@link Object#toString()}. <p> <p>Please override it. It may be removed in a future major version.
 */
@Override public String getXPathNodeName(){
  LOG.warning("getXPathNodeName should be overriden in classes derived from AbstractNode. " + "The implementation is provided for compatibility with existing implementors," + "but could be declared abstract as soon as release " + PMDVersion.getNextMajorRelease() + ".");
  return toString();
}
