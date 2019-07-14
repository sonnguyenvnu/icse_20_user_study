/** 
 * Creates a Transition for the component with the given transition key of the given type. 
 */
public static TransitionUnitsBuilder create(TransitionKeyType type,String key){
  return new TransitionUnitsBuilder(getComponentTargetTypeForTransitionKeyType(type,true),key);
}
