/** 
 * @deprecated use {@link #isExactlyAny(TypedNameDeclaration,Class)}
 */
@Deprecated public static boolean isA(TypedNameDeclaration vnd,Class<?> clazz){
  return isExactlyAny(vnd,clazz);
}
