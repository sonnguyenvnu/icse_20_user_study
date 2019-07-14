/** 
 * Pretty-prints a Type for use in diagnostics, using simple names for class types 
 */
public static String prettyType(Type type){
  return type.accept(PRETTY_TYPE_VISITOR,null);
}
