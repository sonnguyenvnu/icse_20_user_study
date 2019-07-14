/** 
 * @deprecated use {@link #isExactlyAny(TypedNameDeclaration,Class)}
 */
@Deprecated public static boolean isEither(TypedNameDeclaration vnd,Class<?> class1,Class<?> class2){
  return isExactlyAny(vnd,class1,class2);
}
