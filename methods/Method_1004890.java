/** 
 * Note this does not include the matchedVertex field.
 * @param edge the reference Edge with which to compare.
 * @return {@code true} if this object is the same as the edgeargument;  {@code false} otherwise.
 */
public boolean equals(final Edge edge){
  return null != edge && new EqualsBuilder().append(isDirected(),edge.isDirected()).append(getSource(),edge.getSource()).append(getDestination(),edge.getDestination()).appendSuper(super.equals(edge)).isEquals();
}
