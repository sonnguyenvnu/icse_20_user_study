/** 
 * Returns whether a given integer matches a DTS sync word. Synchronization and storage modes are defined in ETSI TS 102 114 V1.1.1 (2002-08), Section 5.3.
 * @param word An integer.
 * @return Whether a given integer matches a DTS sync word.
 */
public static boolean isSyncWord(int word){
  return word == SYNC_VALUE_BE || word == SYNC_VALUE_LE || word == SYNC_VALUE_14B_BE || word == SYNC_VALUE_14B_LE;
}
