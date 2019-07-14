/** 
 * This converts from a set of booleans to OCTAL permissions notations. For use with  {@link RootUtils#CHMOD_COMMAND}(true, false, false,  true, true, false,  false, false, true) => 0461
 */
public static int permissionsToOctalString(boolean ur,boolean uw,boolean ux,boolean gr,boolean gw,boolean gx,boolean or,boolean ow,boolean ox){
  int u=((ur ? CHMOD_READ : 0) | (uw ? CHMOD_WRITE : 0) | (ux ? CHMOD_EXECUTE : 0)) << 6;
  int g=((gr ? CHMOD_READ : 0) | (gw ? CHMOD_WRITE : 0) | (gx ? CHMOD_EXECUTE : 0)) << 3;
  int o=(or ? CHMOD_READ : 0) | (ow ? CHMOD_WRITE : 0) | (ox ? CHMOD_EXECUTE : 0);
  return u | g | o;
}
