/** 
 * Returns a Boolean value that indicates whether two selectors are equal. <p>sel_isEqual is equivalent to  {@code ==}.</p>
 * @param lhs the selector to compare with {@code rhs}
 * @param rhs the selector to compare with {@code lhs}
 * @return {@link #YES} if rhs and rhs are equal, otherwise {@link #NO}
 */
@NativeType("BOOL") public static boolean sel_isEqual(@NativeType("SEL") long lhs,@NativeType("SEL") long rhs){
  long __functionAddress=Functions.sel_isEqual;
  if (CHECKS) {
    check(lhs);
    check(rhs);
  }
  return invokePPZ(lhs,rhs,__functionAddress);
}
