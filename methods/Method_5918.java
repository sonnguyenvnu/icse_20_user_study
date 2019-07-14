/** 
 * Clears prefix flags, as used by  {@link #findNalUnit(byte[],int,int,boolean[])}.
 * @param prefixFlags The flags to clear.
 */
public static void clearPrefixFlags(boolean[] prefixFlags){
  prefixFlags[0]=false;
  prefixFlags[1]=false;
  prefixFlags[2]=false;
}
