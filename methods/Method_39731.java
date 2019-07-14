/** 
 * Builds advice field name.
 */
public static String adviceFieldName(final String name,final int index){
  return ProxettaNames.fieldPrefix + name + ProxettaNames.fieldDivider + index;
}
