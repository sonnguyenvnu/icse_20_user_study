/** 
 * Equivalent to calling  {@code join(null, onLoseMembership)}.
 */
public final Membership join(@Nullable final Command onLoseMembership) throws JoinException, InterruptedException {
  return join(NO_MEMBER_DATA,onLoseMembership);
}
