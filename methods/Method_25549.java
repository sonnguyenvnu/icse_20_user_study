/** 
 * Return true if the given type is 'void' or 'Void'. 
 */
public static boolean isVoidType(Type type,VisitorState state){
  if (type == null) {
    return false;
  }
  return type.getKind() == TypeKind.VOID || state.getTypes().isSameType(Suppliers.JAVA_LANG_VOID_TYPE.get(state),type);
}
