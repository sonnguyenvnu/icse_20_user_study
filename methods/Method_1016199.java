/** 
 * Returns whether the specified component belongs to this container or not.
 * @param component component to process
 * @return true if the specified component belongs to this container, false otherwise
 */
public boolean contains(final Component component){
  return component != null && component.getParent() == this;
}
