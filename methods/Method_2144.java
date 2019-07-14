/** 
 * Checks whether the specified limit flag is present in the limits provided. <p> If the flag contains multiple flags together using a bitwise OR, this only checks that at least one of the flags is included.
 * @param limits the limits to apply
 * @param flag the limit flag(s) to check for
 * @return true if the flag (or one of the flags) is included in the limits
 */
private static boolean shouldLimit(@LimitFlag int limits,@LimitFlag int flag){
  return (limits & flag) != LIMIT_NONE;
}
