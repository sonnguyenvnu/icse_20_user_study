/** 
 * Creates a Transition for the components with the given transition keys of the given type. 
 */
public static TransitionUnitsBuilder create(TransitionKeyType type,String... keys){
  return new TransitionUnitsBuilder(getComponentTargetTypeForTransitionKeyType(type,false),keys);
}
