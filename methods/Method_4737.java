/** 
 * Returns whether an integer matches an ADTS SYNC word. 
 */
public static boolean isAdtsSyncWord(int candidateSyncWord){
  return (candidateSyncWord & 0xFFF6) == 0xFFF0;
}
