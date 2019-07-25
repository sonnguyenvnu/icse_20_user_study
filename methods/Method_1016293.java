/** 
 * Parses the given string.
 * @param string the string to parse
 * @param object the blackboard object. It can be {@code null}.
 * @return the behavior tree
 * @throws SerializationException if the string cannot be successfully parsed. 
 */
public BehaviorTree<E> parse(String string,E object){
  btReader.parse(string);
  return createBehaviorTree(btReader.root,object);
}
