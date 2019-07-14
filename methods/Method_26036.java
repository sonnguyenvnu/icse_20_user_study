/** 
 * Gets the unboxed type, or the original type if it is not unboxable. 
 */
private Type unboxedTypeOrType(VisitorState state,Type type){
  Types types=state.getTypes();
  return types.unboxedTypeOrType(type);
}
