/** 
 * @deprecated use {@link #isExactlyNone(TypedNameDeclaration,Class)}
 */
@Deprecated public static boolean isNeither(TypedNameDeclaration vnd,Class<?> class1,Class<?> class2){
  return !isA(vnd,class1) && !isA(vnd,class2);
}
