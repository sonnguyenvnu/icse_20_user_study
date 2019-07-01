/** 
 * Get the largest cache id in a specified node /level1/level2.
 * @param level1 1st level node name
 * @param level2 2nd level node name
 * @return the largest ledger id
 */
private long _XXXXX_(String level1,String level2) throws IOException {
  return getLedgerId(level1,level2,MAX_ID_SUFFIX);
}