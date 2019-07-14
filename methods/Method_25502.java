/** 
 * Given the string representation of a type, supplies the corresponding type.
 * @param typeString a string representation of a type, e.g., "java.util.List"
 */
public static Supplier<Type> typeFromString(final String typeString){
  requireNonNull(typeString);
  return new Supplier<Type>(){
    @Override public Type get(    VisitorState state){
      return state.getTypeFromString(typeString);
    }
  }
;
}
