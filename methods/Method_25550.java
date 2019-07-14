/** 
 * Returns true if  {@code t} is a subtype of Exception but not a subtype of RuntimeException. 
 */
public static boolean isCheckedExceptionType(Type t,VisitorState state){
  Symtab symtab=state.getSymtab();
  return isSubtype(t,symtab.exceptionType,state) && !isSubtype(t,symtab.runtimeExceptionType,state);
}
