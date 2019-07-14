/** 
 * Converts an old style id to a new style id.
 * @param id  the old style id
 * @return the new style id, null if not found
 */
private static String getConvertedId(String id){
  return LazyInit.CONVERSION_MAP.get(id);
}
