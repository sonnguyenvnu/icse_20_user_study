/** 
 * Indicates whether this action implies the given action.  The given action is implied if it is present in the hierarchy of this action.
 * @param action the action to check
 * @return true if it is implied by this action
 */
default boolean implies(Action action){
  return getHierarchy().stream().anyMatch(a -> a.equals(action));
}
