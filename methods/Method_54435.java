/** 
 * Validates pointer members that should not be  {@code NULL}.
 * @param struct the struct to validate
 */
public static void validate(long struct){
  check(memGetAddress(struct + B3DebugLines.M_LINESFROM));
  check(memGetAddress(struct + B3DebugLines.M_LINESTO));
  check(memGetAddress(struct + B3DebugLines.M_LINESCOLOR));
}
